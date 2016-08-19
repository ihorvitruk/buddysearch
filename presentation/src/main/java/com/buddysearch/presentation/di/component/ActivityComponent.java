package com.buddysearch.presentation.di.component;

import com.buddysearch.presentation.di.module.ActivityModule;
import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.ui.activity.LoginActivity;
import com.buddysearch.presentation.ui.activity.SplashActivity;
import com.buddysearch.presentation.ui.activity.UsersActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);

    void inject(SplashActivity splashActivity);

    void inject(UsersActivity usersActivity);
}
