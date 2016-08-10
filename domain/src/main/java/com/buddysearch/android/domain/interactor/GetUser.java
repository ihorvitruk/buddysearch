package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.entity.User;
import com.buddysearch.android.domain.executor.PostExecutionThread;
import com.buddysearch.android.domain.executor.ThreadExecutor;
import com.buddysearch.android.domain.repository.UserRepository;

import rx.Observable;

public class GetUser extends UseCase<User, UserRepository> {

    private final String userId;

    protected GetUser(String userId, UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(userRepository, threadExecutor, postExecutionThread);
        this.userId = userId;
    }

    @Override
    protected Observable<User> buildUseCaseObservable() {
        return repository.getUser(userId);
    }
}
