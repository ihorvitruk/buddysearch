package com.buddysearch.presentation.di.module;

import android.content.Context;

import com.buddysearch.presentation.App;
import com.buddysearch.presentation.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    Context provideApplicationContext() {
        return app;
    }
}
