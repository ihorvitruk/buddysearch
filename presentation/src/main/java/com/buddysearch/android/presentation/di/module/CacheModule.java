package com.buddysearch.android.presentation.di.module;

import com.buddysearch.android.data.mapper.realm.RealmMessageEntityMapper;
import com.buddysearch.android.data.mapper.realm.RealmUserEntityMapper;
import com.buddysearch.android.data.store.cache.MessageCache;
import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.data.store.cache.realm.RealmMessageCache;
import com.buddysearch.android.data.store.cache.realm.RealmUserCache;
import com.buddysearch.android.presentation.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    @Provides
    @AppScope
    UserCache providesUserCache(RealmUserEntityMapper realmUserEntityMapper) {
        return new RealmUserCache(realmUserEntityMapper);
    }

    @Provides
    @AppScope
    MessageCache providesMessageCache(RealmMessageEntityMapper realmMessageEntityMapper) {
        return new RealmMessageCache(realmMessageEntityMapper);
    }
}
