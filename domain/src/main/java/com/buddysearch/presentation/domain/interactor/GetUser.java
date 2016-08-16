package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.domain.executor.PostExecutionThread;
import com.buddysearch.presentation.domain.executor.ThreadExecutor;
import com.buddysearch.presentation.domain.repository.UserRepository;

import rx.Observable;

public class GetUser extends UseCase<String, User, UserRepository> {

    public GetUser(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(userRepository, threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<User> buildObservable(String userId) {
        return repository.getUser(userId);
    }
}
