package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.repository.UserRepository;

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
        return new GetUser(FAKE_USER_ID, mockRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Override
    protected UserRepository createRepository() {
        return mock(UserRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(() -> verify(mockRepository).getUser(FAKE_USER_ID));
    }
}
