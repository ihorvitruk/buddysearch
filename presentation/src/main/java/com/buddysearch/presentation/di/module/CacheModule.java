package com.buddysearch.presentation.di.module;

import android.content.Context;

import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.data.store.cache.realm.RealmUserCache;
import com.buddysearch.presentation.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    private Context context;

    public CacheModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    UserCache providesUserCache() {
        return new RealmUserCache(context);
    }
}
