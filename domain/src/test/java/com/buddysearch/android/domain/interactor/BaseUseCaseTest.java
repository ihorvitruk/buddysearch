package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.executor.PostExecutionThread;
import com.buddysearch.android.domain.executor.ThreadExecutor;
import com.buddysearch.android.domain.repository.Repository;

import org.junit.Before;
import org.mockito.Mock;

import rx.functions.Action0;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public abstract class BaseUseCaseTest<USECASE extends UseCase, REPOSITORY extends Repository> {

    protected USECASE useCase;

    @Mock
    protected REPOSITORY mockRepository;

    @Mock
    protected ThreadExecutor mockThreadExecutor;

    @Mock
    protected PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        useCase = createUseCase();
    }

    protected abstract USECASE createUseCase();

    protected abstract void testBuildUseCaseObservable();

    protected void testBuildUseCaseObservable(Action0 action) {
        useCase.buildUseCaseObservable();
        action.call();
        verifyNoMoreInteractions(mockRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }
}
