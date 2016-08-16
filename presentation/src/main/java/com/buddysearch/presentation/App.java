package com.buddysearch.presentation;

import android.app.Application;

import com.buddysearch.presentation.di.component.AppComponent;
import com.buddysearch.presentation.di.module.AppModule;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initializeLeakDetection() {
        LeakCanary.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
