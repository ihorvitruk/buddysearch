package com.buddysearch.presentation.di.module;

import android.content.Context;

import com.buddysearch.presentation.App;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.manager.impl.NetworkManagerImpl;

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
    @Singleton
    NetworkManager providesNetworkManager() {
        return new NetworkManagerImpl(app);
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
}
