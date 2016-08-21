package com.buddysearch.presentation.di.module;

import com.buddysearch.android.data.mapper.UserEntityMapper;
import com.buddysearch.android.data.repository.UserDataRepository;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.domain.repository.UserRepository;

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
