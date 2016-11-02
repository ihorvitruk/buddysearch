package com.buddysearch.android.presentation.di.module;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.store.MessageEntityStore;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.firebase.FirebaseMessageEntityStore;
import com.buddysearch.android.data.store.firebase.FirebaseUserEntityStore;
import com.buddysearch.android.presentation.App;
import com.buddysearch.android.presentation.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class EntityStoreModule {

    @Provides
    @AppScope
    UserEntityStore providesUserEntityStore() {
        return new FirebaseUserEntityStore();
    }

    @Provides
    @AppScope
    MessageEntityStore providesMessageEntityStore(AuthManager authManager, App app) {
        return new FirebaseMessageEntityStore(authManager, app);
    }
}
