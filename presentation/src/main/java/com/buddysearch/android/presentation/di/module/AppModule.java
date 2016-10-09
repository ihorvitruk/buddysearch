package com.buddysearch.android.presentation.di.module;

import android.content.Context;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.impl.AuthManagerImpl;
import com.buddysearch.android.domain.interactor.user.CreateUser;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.manager.impl.NetworkManagerImpl;
import com.buddysearch.android.presentation.App;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.ActivityScope;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    Context provideAppContext() {
        return app;
    }

    @Provides
    @Named("Thread")
    @Singleton
    Scheduler providesThreadScheduler() {
        return Schedulers.io();
    }


    @Provides
    @Named("PostExecution")
    @Singleton
    Scheduler providesPostExecutionScheduler() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @Singleton
    GoogleApiClient providesGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(app.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        return new GoogleApiClient.Builder(app)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Provides
    @Singleton
    NetworkManager providesNetworkManager() {
        return new NetworkManagerImpl(app);
    }


    @Provides
    @Singleton
    AuthManager providesAuthManager(GoogleApiClient googleApiClient) {
        return new AuthManagerImpl(googleApiClient);
    }
}
