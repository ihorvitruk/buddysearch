package com.buddysearch.android.presentation.di.module;

import com.buddysearch.android.data.mapper.MessageEntityDtoMapper;
import com.buddysearch.android.data.mapper.UserEntityDtoMapper;
import com.buddysearch.android.data.repository.MessageRepositoryImpl;
import com.buddysearch.android.data.repository.UserRepositoryImpl;
import com.buddysearch.android.data.store.MessageEntityStore;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.cache.MessageCache;
import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.domain.repository.MessageRepository;
import com.buddysearch.android.domain.repository.UserRepository;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.presentation.di.scope.ActivityScope;
import com.buddysearch.android.presentation.ui.activity.BaseDaggerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @ActivityScope
    UserRepository providesUserRepository(NetworkManager networkManager,
                                          BaseDaggerActivity activity,
                                          UserEntityStore userEntityStore,
                                          UserCache userCache,
                                          UserEntityDtoMapper userEntityDtoMapper) {
        return new UserRepositoryImpl(networkManager, activity.getView(),
                userEntityStore, userCache, userEntityDtoMapper);
    }

    @Provides
    @ActivityScope
    MessageRepository providesMessageRepository(NetworkManager networkManager,
                                                BaseDaggerActivity activity,
                                                MessageEntityStore messageEntityStore,
                                                MessageCache messageCache,
                                                MessageEntityDtoMapper messageEntityDtoMapper) {
        return new MessageRepositoryImpl(networkManager, activity.getView(), messageEntityStore, messageCache, messageEntityDtoMapper);
    }
}
