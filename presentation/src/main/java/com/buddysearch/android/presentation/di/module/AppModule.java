package com.buddysearch.android.presentation.di.module;

import android.content.Context;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.impl.AuthManagerImpl;
import com.buddysearch.android.domain.interactor.user.CreateUser;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.manager.impl.NetworkManagerImpl;
import com.buddysearch.android.presentation.App;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.AppScope;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Named;

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
    @AppScope
    Context provideAppContext() {
        return app;
    }

    @Provides
    @Named("Thread")
    @AppScope
    Scheduler providesThreadScheduler() {
        return Schedulers.io();
    }


    @Provides
    @Named("PostExecution")
    @AppScope
    Scheduler providesPostExecutionScheduler() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @AppScope
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
    @AppScope
    NetworkManager providesNetworkManager() {
        return new NetworkManagerImpl(app);
    }

    @Provides
    @AppScope
    AuthManager providesAuthManager(CreateUser createUser, GoogleApiClient googleApiClient) {
        return new AuthManagerImpl(createUser, googleApiClient);
    }
}
