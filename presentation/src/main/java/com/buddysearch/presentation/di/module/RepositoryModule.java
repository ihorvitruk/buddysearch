package com.buddysearch.presentation.di.module;

import com.buddysearch.presentation.data.mapper.UserEntityMapper;
import com.buddysearch.presentation.data.repository.UserDataRepository;
import com.buddysearch.presentation.data.store.UserEntityStore;
import com.buddysearch.presentation.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    UserRepository providesUserRepository(UserEntityStore userEntityStore, UserEntityMapper userEntityMapper) {
        return new UserDataRepository(userEntityStore, userEntityMapper);
    }
}
