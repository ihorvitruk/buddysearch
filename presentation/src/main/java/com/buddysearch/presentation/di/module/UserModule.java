package com.buddysearch.presentation.di.module;

import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.domain.executor.PostExecutionThread;
import com.buddysearch.presentation.domain.executor.ThreadExecutor;
import com.buddysearch.presentation.domain.interactor.GetUser;
import com.buddysearch.presentation.domain.interactor.GetUsers;
import com.buddysearch.presentation.domain.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    private String userId;

    public UserModule() {
    }

    public UserModule(String userId) {
        this.userId = userId;
    }

    @Provides
    @ActivityScope
    GetUser getUser(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetUser(userId, userRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @ActivityScope
    GetUsers getUsers(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetUsers(userRepository, threadExecutor, postExecutionThread);
    }
}
