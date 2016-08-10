package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.executor.PostExecutionThread;
import com.buddysearch.android.domain.executor.ThreadExecutor;
import com.buddysearch.android.domain.repository.Repository;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTest extends BaseUseCaseTest<UseCaseTest.TestUserCase, UseCaseTest.TestRepository> {

    private TestSubscriber<Integer> testSubscriber;

    @Override
    public void setUp() {
        super.setUp();
        testSubscriber = new TestSubscriber<>();
    }

    @Override
    protected TestUserCase createUseCase() {
        return new TestUserCase(mockRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Override
    protected TestRepository createRepository() {
        TestRepository testRepository = mock(TestRepository.class);
        when(testRepository.getData()).thenReturn(Observable.empty());
        return testRepository;
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(() -> verify(mockRepository).getData());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void buildUseCaseObservable_AsCorrectResult() {
        TestScheduler testScheduler = new TestScheduler();
        given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        useCase.execute(testSubscriber);

        assertThat(testSubscriber.getOnNextEvents().size(), is(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void unsubscribe_AfterExecute_AsUnsubscribe() {
        assertThat(useCase.isUnsubscribed(), Is.is(false));
        useCase.unsubscribe();
        assertThat(useCase.isUnsubscribed(), Is.is(true));

        useCase.execute(testSubscriber);
        useCase.unsubscribe();

        assertThat(useCase.isUnsubscribed(), Is.is(true));
    }

    class TestUserCase extends UseCase<Integer, TestRepository> {

        TestUserCase(TestRepository repository,
                     ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread) {
            super(repository, threadExecutor, postExecutionThread);
        }

        @Override
        protected Observable<Integer> buildUseCaseObservable() {
            return repository.getData();
        }
    }

    interface TestRepository extends Repository {
        Observable<Integer> getData();
    }
}
