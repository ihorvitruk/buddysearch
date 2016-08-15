package com.buddysearch.presentation;

import android.app.Application;

import com.buddysearch.presentation.di.component.ApplicationComponent;
import com.buddysearch.presentation.di.component.DaggerApplicationComponent;
import com.buddysearch.presentation.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeLeakDetection() {
        LeakCanary.install(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
