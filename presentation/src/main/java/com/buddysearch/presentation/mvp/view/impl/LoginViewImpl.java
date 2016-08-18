package com.buddysearch.presentation.mvp.view.impl;

import com.buddysearch.presentation.mvp.view.LoginView;
import com.buddysearch.presentation.ui.activity.BaseActivity;

public abstract class LoginViewImpl extends ViewImpl implements LoginView {
    public LoginViewImpl(BaseActivity activity) {
        super(activity);
    }
}
