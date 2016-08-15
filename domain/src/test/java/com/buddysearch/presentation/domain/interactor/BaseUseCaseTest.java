package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.executor.PostExecutionThread;
import com.buddysearch.presentation.domain.executor.ThreadExecutor;
import com.buddysearch.presentation.domain.repository.Repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.functions.Action0;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseUseCaseTest<USE_CASE extends UseCase, REPOSITORY extends Repository> {

    protected USE_CASE useCase;

    protected REPOSITORY mockRepository;

    @Mock
    protected ThreadExecutor mockThreadExecutor;

    @Mock
    protected PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        mockRepository = createRepository();
        useCase = createUseCase();
    }

    protected abstract USE_CASE createUseCase();

    protected abstract REPOSITORY createRepository();

    public abstract void testBuildUseCaseObservable();

    protected void testBuildUseCaseObservable(Action0 action) {
        useCase.buildUseCaseObservable();
        action.call();
        verifyNoMoreInteractions(mockRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }
}
