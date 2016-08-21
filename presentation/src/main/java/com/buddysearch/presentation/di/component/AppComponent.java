package com.buddysearch.presentation.di.component;

import com.buddysearch.presentation.di.module.ActivityModule;
import com.buddysearch.presentation.di.module.AppModule;
import com.buddysearch.presentation.di.module.CacheModule;
import com.buddysearch.presentation.di.module.EntityStoreModule;
import com.buddysearch.presentation.di.module.RepositoryModule;

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
