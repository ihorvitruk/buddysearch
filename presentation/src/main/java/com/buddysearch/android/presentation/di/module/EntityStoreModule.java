package com.buddysearch.android.presentation.di.module;

import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.firebase.FirebaseUserEntityStore;
import com.buddysearch.android.presentation.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityStoreModule {

    @Provides
    @ActivityScope
    UserEntityStore providesUserEntityStore() {
        return new FirebaseUserEntityStore();
    }
}
