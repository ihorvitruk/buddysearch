package com.buddysearch.presentation.di.module;

import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.data.store.cache.realm.RealmUserCache;
import com.buddysearch.presentation.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    @Provides
    @ActivityScope
    UserCache providesUserCache() {
        return new RealmUserCache();
    }
}
