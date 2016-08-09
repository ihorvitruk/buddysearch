package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.repository.UserRepository;

import org.junit.Test;

import static org.mockito.Mockito.verify;

public class GetUserTest extends BaseUseCaseTest<GetUser, UserRepository> {

    private static final String FAKE_USER_ID = "123";

    @Override
    protected GetUser createUseCase() {
        return new GetUser(FAKE_USER_ID, mockRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    @Override
    protected void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(() -> verify(mockRepository).getUser(FAKE_USER_ID));
    }
}
