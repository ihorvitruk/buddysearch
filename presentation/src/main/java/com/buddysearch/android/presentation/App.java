package com.buddysearch.android.presentation;

import android.app.Application;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.presentation.di.component.AppComponent;
import com.buddysearch.android.presentation.di.component.DaggerAppComponent;
import com.buddysearch.android.presentation.di.module.AppModule;
import com.squareup.leakcanary.LeakCanary;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Inject
    NetworkManager networkManager;

    @Inject
    AuthManager authManager;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initializeInjector();
        initializeLeakDetection();
        networkManager.start();

        if (authManager.isSignedIn()) {
            FirebaseMessaging.getInstance().subscribeToTopic("user_" + authManager.getCurrentUserId());
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        networkManager.stop();
        if (authManager.isSignedIn()) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("user_" + authManager.getCurrentUserId());
        }
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
        appComponent.inject(this);
    }

    private void initializeLeakDetection() {
        LeakCanary.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
