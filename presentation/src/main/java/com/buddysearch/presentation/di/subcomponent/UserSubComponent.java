package com.buddysearch.presentation.di.subcomponent;

import com.buddysearch.presentation.di.module.UserModule;
import com.buddysearch.presentation.di.scope.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = UserModule.class)
public interface UserSubComponent {
}
