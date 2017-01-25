package com.buddysearch.android.domain.interactor.user;

import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.functions.Action;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTest extends BaseUseCaseTest<GetUsers, UserRepository> {

    @Override
    protected GetUsers createUseCase() {
        return new GetUsers(mockRepository, mockMessenger, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected UserRepository createRepository() {
        return mock(UserRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() throws Exception {
        testBuildUseCaseObservable(null, new Action() {
            @Override
            public void run() {
                verify(mockRepository).getUsers(mockMessenger);
            }
        });
    }
}
