package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.executor.PostExecutionThread;
import com.buddysearch.android.domain.executor.ThreadExecutor;
import com.buddysearch.android.domain.repository.Repository;

import org.hamcrest.core.Is;
import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UseCaseTest extends BaseUseCaseTest<UseCaseTest.TestUserCase, UseCaseTest.TestRepository> {

    private TestSubscriber testSubscriber;

    @Override
    public void setUp() {
        super.setUp();
        testSubscriber = new TestSubscriber();

    }

    @Override
    protected TestUserCase createUseCase() {
        return new TestUserCase(mockRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    @Override
    protected void testBuildUseCaseObservable() {
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
        useCase.execute(testSubscriber);
        useCase.unsubscribe();

        assertThat(testSubscriber.isUnsubscribed(), Is.is(true));
    }

    static class TestUserCase extends UseCase {

        TestUserCase(Repository repository,
                     ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread) {
            super(repository, threadExecutor, postExecutionThread);
        }

        @Override
        protected Observable buildUseCaseObservable() {
            return Observable.empty();
        }
    }

    interface TestRepository extends Repository {
        Observable getData();
    }
}
