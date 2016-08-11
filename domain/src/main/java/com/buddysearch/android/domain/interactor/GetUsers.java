package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.dto.User;
import com.buddysearch.android.domain.executor.PostExecutionThread;
import com.buddysearch.android.domain.executor.ThreadExecutor;
import com.buddysearch.android.domain.repository.UserRepository;

import java.util.List;

import rx.Observable;

public class GetUsers extends UseCase<List<User>, UserRepository> {

    protected GetUsers(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(userRepository, threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<List<User>> buildUseCaseObservable() {
        return repository.getUsers();
    }
}
