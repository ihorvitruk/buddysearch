package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.repository.Repository;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTest extends BaseUseCaseTest<UseCaseTest.TestUseCase, UseCaseTest.TestRepository> {

    private TestSubscriber<Integer> testSubscriber;

    @Override
    public void setUp() {
        super.setUp();
        testSubscriber = new TestSubscriber<>();
    }

    @Override
    protected TestUseCase createUseCase() {
        return new TestUseCase(mockRepository, mockThreadScheduler, new TestScheduler());
    }

    @Override
    protected TestRepository createRepository() {
        TestRepository testRepository = mock(TestRepository.class);
        when(testRepository.getData()).thenReturn(Observable.just(Integer.MAX_VALUE));
        return testRepository;
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(null, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).getData();
            }
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    public void buildUseCaseObservable_AsCorrectResult() {
        useCase.execute(testSubscriber);
        assertThat(testSubscriber.getOnNextEvents().size(), is(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void unsubscribe_AfterExecute_AsUnsubscribed() {
        assertThat(useCase.isUnsubscribed(), Is.is(true));
        useCase.execute(testSubscriber);
        // TODO change useCase for longer action to uncomment this line
        //assertThat(useCase.isUnsubscribed(), Is.is(false));
        useCase.unsubscribe();
        assertThat(useCase.isUnsubscribed(), Is.is(true));
    }

    class TestUseCase extends UseCase1<Integer, TestRepository> {

        public TestUseCase(TestRepository repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
            super(repository, threadScheduler, postExecutionScheduler);
        }

        @Override
        protected Observable<Integer> buildObservable() {
            return repository.getData();
        }
    }

    interface TestRepository extends Repository {
        Observable<Integer> getData();
    }
}
