package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersTest extends BaseUseCaseTest<GetUsers, UserRepository> {

    @Override
    protected GetUsers createUseCase() {
        return new GetUsers(mockRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Override
    protected UserRepository createRepository() {
        return mock(UserRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(null, () -> verify(mockRepository).getUsers());
    }
}
