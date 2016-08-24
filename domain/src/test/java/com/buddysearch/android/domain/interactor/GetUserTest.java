package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetUserTest extends BaseUseCaseTest<GetUser, UserRepository> {

    private static final String FAKE_USER_ID = "123";

    @Override
    protected GetUser createUseCase() {
        return new GetUser(mockRepository, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected UserRepository createRepository() {
        return mock(UserRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(FAKE_USER_ID, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).getUser(FAKE_USER_ID);
            }
        });
    }
}
