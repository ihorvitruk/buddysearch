package com.buddysearch.presentation.di.component;

import com.buddysearch.presentation.di.module.ApplicationModule;
import com.buddysearch.presentation.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
}
