package com.buddysearch.presentation.di.module;

import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.android.data.mapper.UserEntityMapper;
import com.buddysearch.android.data.repository.UserDataRepository;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.domain.repository.UserRepository;
import com.buddysearch.presentation.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @ActivityScope
    UserRepository providesUserRepository(NetworkManager networkManager,
                                          UserEntityStore userEntityStore,
                                          UserCache userCache,
                                          UserEntityMapper userEntityMapper) {
        return new UserDataRepository(networkManager,
                userEntityStore, userCache, userEntityMapper);
    }
}
