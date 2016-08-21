package com.buddysearch.presentation.di.module;

import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.firebase.FirebaseUserEntityStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityStoreModule {

    @Provides
    @Singleton
    UserEntityStore providesUserEntityStore() {
        return new FirebaseUserEntityStore();
    }
}
