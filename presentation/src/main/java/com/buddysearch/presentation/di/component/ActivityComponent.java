package com.buddysearch.presentation.di.component;

import com.buddysearch.presentation.di.module.ActivityModule;
import com.buddysearch.presentation.di.scope.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
}
