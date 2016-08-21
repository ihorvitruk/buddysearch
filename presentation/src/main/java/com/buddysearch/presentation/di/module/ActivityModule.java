package com.buddysearch.presentation.di.module;

import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.manager.impl.AuthManagerImpl;
import com.buddysearch.presentation.manager.impl.NetworkManagerImpl;
import com.buddysearch.presentation.ui.activity.BaseActivity;
import com.google.android.gms.common.api.GoogleApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    AuthManager providesAuthManager(GoogleApiClient googleApiClient) {
        return new AuthManagerImpl(googleApiClient);
    }

    @Provides
    //must not have scopes. See usages
    NetworkManager providesNetworkManager() {
        return new NetworkManagerImpl(activity);
    }
}
