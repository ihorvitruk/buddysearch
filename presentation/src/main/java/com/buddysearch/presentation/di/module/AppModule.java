package com.buddysearch.presentation.di.module;

import android.content.Context;

import com.buddysearch.presentation.App;
import com.buddysearch.presentation.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

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
}
