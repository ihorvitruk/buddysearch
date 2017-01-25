package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.repository.Repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Scheduler;
import io.reactivex.functions.Action;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseUseCaseTest<USE_CASE extends UseCase, REPOSITORY extends Repository> {

    protected USE_CASE useCase;

    protected REPOSITORY mockRepository;

    @Mock
    protected Messenger mockMessenger;

    @Mock
    protected Scheduler mockThreadScheduler;

    @Mock
    protected Scheduler mockPostExecutionScheduler;

    @Before
    public void setUp() {
        mockRepository = createRepository();
        useCase = createUseCase();
    }

    protected abstract USE_CASE createUseCase();

    protected abstract REPOSITORY createRepository();

    public abstract void testBuildUseCaseObservable() throws Exception;

    protected void testBuildUseCaseObservable(Object params, Action action) throws Exception {
        useCase.buildObservable(params);
        action.run();
        verifyNoMoreInteractions(mockRepository);
        verifyZeroInteractions(mockThreadScheduler);
        verifyZeroInteractions(mockPostExecutionScheduler);
    }
}
