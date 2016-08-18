package com.buddysearch.presentation.di.module;

import android.util.Log;

import com.buddysearch.presentation.ui.activity.BaseActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
    GoogleApiClient providesGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        return new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, connectionResult -> Log.e("GoogleApiClient", "Google Play Services error."))
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }
}
