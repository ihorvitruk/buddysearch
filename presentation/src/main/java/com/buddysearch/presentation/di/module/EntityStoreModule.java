package com.buddysearch.presentation.di.module;

import com.buddysearch.presentation.data.store.UserEntityStore;
import com.buddysearch.presentation.data.store.firebase.FirebaseUserEntityStore;

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
