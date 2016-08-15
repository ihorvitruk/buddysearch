package com.buddysearch.presentation.di.component;

import com.buddysearch.presentation.di.module.ActivityModule;
import com.buddysearch.presentation.di.module.UserModule;
import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.di.subcomponent.UserSubComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    UserSubComponent plus(UserModule userModule);
}
