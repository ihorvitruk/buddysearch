package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.repository.Repository;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTest extends BaseUseCaseTest<UseCaseTest.TestUseCase, UseCaseTest.TestRepository> {

    private TestDisposableObserver<Integer> testObserver;

    @Override
    public void setUp() {
        testObserver = new TestDisposableObserver<>();
        super.setUp();
    }

    @Override
    protected TestUseCase createUseCase() {
        return new TestUseCase(mockRepository, mockMessenger, new TestScheduler(), new TestScheduler());
    }

    @Override
    protected TestRepository createRepository() {
        TestRepository testRepository = mock(TestRepository.class);
        when(testRepository.getData()).thenReturn(Observable.just(Integer.MAX_VALUE));
        return testRepository;
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() throws Exception {
        testBuildUseCaseObservable(null, new Action() {
            @Override
            public void run() throws Exception {
                verify(mockRepository).getData();
            }
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    public void buildUseCaseObservable_AsCorrectResult() {
        useCase.execute(testObserver);
        assertThat(testObserver.valuesCount, is(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void dispose_AfterExecute_AsDisposed() {
        assertThat(useCase.isDisposed(), Is.is(true));
        useCase.execute(testObserver);
        // TODO change useCase for longer action to uncomment this line
        //assertThat(useCase.isDisposed(), Is.is(false));
        useCase.dispose();
        assertThat(useCase.isDisposed(), Is.is(true));
    }

    class TestUseCase extends UseCase1<Integer, TestRepository> {


        public TestUseCase(TestRepository repository,
                           Messenger messenger,
                           @Named("Thread") Scheduler threadScheduler,
                           @Named("PostExecution") Scheduler postExecutionScheduler) {
            super(repository, messenger, threadScheduler, postExecutionScheduler);
        }

        @Override
        protected Observable<Integer> buildObservable() {
            return repository.getData();
        }
    }

    interface TestRepository extends Repository {
        Observable<Integer> getData();
    }

    private static class TestDisposableObserver<T> extends DisposableObserver<T> {
        private int valuesCount = 0;

        @Override
        public void onNext(T value) {
            valuesCount++;
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }
}
