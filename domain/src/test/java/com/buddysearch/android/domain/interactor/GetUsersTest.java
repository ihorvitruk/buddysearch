package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.repository.UserRepository;

import org.junit.Test;

import static org.mockito.Mockito.verify;

public class GetUsersTest extends BaseUseCaseTest<GetUsers, UserRepository> {

    @Override
    protected GetUsers createUseCase() {
        return new GetUsers(mockRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    @Override
    protected void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(() -> verify(mockRepository).getUsers());
    }
}
