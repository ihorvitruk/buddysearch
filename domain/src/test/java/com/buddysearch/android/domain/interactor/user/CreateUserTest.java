package com.buddysearch.android.domain.interactor.user;

import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.UserRepository;

import org.junit.Test;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateUserTest extends BaseUseCaseTest<CreateUser, UserRepository> {

    private final UserDto testUser = new UserDto();

    @Override
    protected CreateUser createUseCase() {
        return new CreateUser(mockRepository, mockMessenger, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected UserRepository createRepository() {
        return mock(UserRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(testUser, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).createUserIfNotExists(testUser, mockMessenger);
            }
        });
    }
}
