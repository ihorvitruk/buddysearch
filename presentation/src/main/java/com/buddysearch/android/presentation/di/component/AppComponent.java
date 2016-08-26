package com.buddysearch.android.presentation.di.component;

import com.buddysearch.android.presentation.di.module.ActivityModule;
import com.buddysearch.android.presentation.di.module.AppModule;
import com.buddysearch.android.presentation.di.module.CacheModule;
import com.buddysearch.android.presentation.di.module.EntityStoreModule;
import com.buddysearch.android.presentation.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    ActivityComponent plus(ActivityModule activityModule,
                           RepositoryModule repositoryModule,
                           EntityStoreModule entityStoreModule,
                           CacheModule cacheModule);
}
