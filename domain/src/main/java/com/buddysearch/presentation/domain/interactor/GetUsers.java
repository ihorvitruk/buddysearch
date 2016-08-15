package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.domain.executor.PostExecutionThread;
import com.buddysearch.presentation.domain.executor.ThreadExecutor;
import com.buddysearch.presentation.domain.repository.UserRepository;

import java.util.List;

import rx.Observable;

public class GetUsers extends UseCase<List<User>, UserRepository> {

    public GetUsers(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(userRepository, threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<List<User>> buildUseCaseObservable() {
        return repository.getUsers();
    }
}
