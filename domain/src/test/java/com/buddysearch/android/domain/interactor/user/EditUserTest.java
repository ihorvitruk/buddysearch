package com.buddysearch.android.domain.interactor.user;

import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.UserRepository;

import org.junit.Test;

import io.reactivex.functions.Action;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EditUserTest extends BaseUseCaseTest<EditUser, UserRepository> {

    private final UserDto testUser = new UserDto();

    @Override
    protected EditUser createUseCase() {
        return new EditUser(mockRepository, mockMessenger, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected UserRepository createRepository() {
        return mock(UserRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() throws Exception {
        testBuildUseCaseObservable(testUser, new Action() {
            @Override
            public void run() {
                verify(mockRepository).editUser(testUser, mockMessenger);
            }
        });
    }
}
