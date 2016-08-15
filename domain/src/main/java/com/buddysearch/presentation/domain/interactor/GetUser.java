package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.domain.executor.PostExecutionThread;
import com.buddysearch.presentation.domain.executor.ThreadExecutor;
import com.buddysearch.presentation.domain.repository.UserRepository;

import rx.Observable;

public class GetUser extends UseCase<User, UserRepository> {

    private final String userId;

    public GetUser(String userId, UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(userRepository, threadExecutor, postExecutionThread);
        this.userId = userId;
    }

    @Override
    protected Observable<User> buildUseCaseObservable() {
        return repository.getUser(userId);
    }
}
