package com.buddysearch.android.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.mvp.view.LoginView;

public abstract class LoginViewImpl extends ViewImpl implements LoginView {
    public LoginViewImpl(BaseActivity activity) {
        super(activity);
    }
}
