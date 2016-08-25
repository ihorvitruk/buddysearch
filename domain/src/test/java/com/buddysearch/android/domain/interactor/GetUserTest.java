package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.domain.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetUserTest extends BaseUseCaseTest<GetUser, UserRepository> {

    private static final String FAKE_USER_ID = "123";

    @Override
    protected GetUser createUseCase() {
        return new GetUser(mockRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Override
    protected UserRepository createRepository() {
        return mock(UserRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(FAKE_USER_ID, () -> verify(mockRepository).getUser(FAKE_USER_ID));
    }
}
