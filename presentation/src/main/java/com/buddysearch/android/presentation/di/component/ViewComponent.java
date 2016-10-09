package com.buddysearch.android.presentation.di.component;

import com.buddysearch.android.presentation.di.module.ViewModule;
import com.buddysearch.android.presentation.di.scope.ViewScope;
import com.buddysearch.android.presentation.ui.activity.DialogActivity;
import com.buddysearch.android.presentation.ui.activity.LoginActivity;
import com.buddysearch.android.presentation.ui.activity.SplashActivity;
import com.buddysearch.android.presentation.ui.activity.UsersActivity;

import dagger.Component;

/**
 * Created by ihor on 10/9/16.
 */

@ViewScope
@Component(modules = {ViewModule.class})
public interface ViewComponent {

    void inject(LoginActivity loginActivity);

    void inject(DialogActivity dialogActivity);

    void inject(SplashActivity splashActivity);

    void inject(UsersActivity usersActivity);
}
