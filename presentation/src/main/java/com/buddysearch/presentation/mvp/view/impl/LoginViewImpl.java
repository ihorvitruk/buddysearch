package com.buddysearch.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.presentation.mvp.view.LoginView;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;

public abstract class LoginViewImpl extends ViewImpl implements LoginView {
    public LoginViewImpl(BaseActivity activity) {
        super(activity);
    }
}
