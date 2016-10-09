package com.buddysearch.android.presentation.di.module;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.impl.AuthManagerImpl;
import com.buddysearch.android.domain.interactor.user.CreateUser;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.manager.impl.NetworkManagerImpl;
import com.buddysearch.android.presentation.di.scope.ActivityScope;
import com.buddysearch.android.presentation.ui.activity.BaseDaggerActivity;
import com.google.android.gms.common.api.GoogleApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private BaseDaggerActivity activity;

    public ActivityModule(BaseDaggerActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    BaseDaggerActivity providesActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    AuthManager providesAuthManager(CreateUser createUser, GoogleApiClient googleApiClient) {
        return new AuthManagerImpl(createUser, googleApiClient);
    }
}
