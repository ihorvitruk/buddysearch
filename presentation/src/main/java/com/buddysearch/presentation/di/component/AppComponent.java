package com.buddysearch.presentation.di.component;

import com.buddysearch.presentation.di.module.AppModule;
import com.buddysearch.presentation.di.scope.AppScope;

import dagger.Component;

@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {
}
