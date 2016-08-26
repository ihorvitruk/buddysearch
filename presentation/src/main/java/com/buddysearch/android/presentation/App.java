package com.buddysearch.android.presentation;

import android.app.Application;

import com.buddysearch.android.presentation.di.component.AppComponent;
import com.buddysearch.android.presentation.di.component.DaggerAppComponent;
import com.buddysearch.android.presentation.di.module.AppModule;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initializeInjector();
        initializeLeakDetection();
    }

    private void initRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
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
