package com.buddysearch.presentation.di.component;

import com.buddysearch.presentation.di.module.ActivityModule;
import com.buddysearch.presentation.di.module.AppModule;
import com.buddysearch.presentation.di.module.RepositoryModule;
import com.buddysearch.presentation.di.module.EntityStoreModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class, EntityStoreModule.class})
public interface AppComponent {
    ActivityComponent plus(ActivityModule activityModule);
}
