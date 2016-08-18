package com.buddysearch.presentation.ui.activity;

import com.buddysearch.presentation.mvp.presenter.LoginPresenter;
import com.buddysearch.presentation.mvp.view.LoginView;
import com.buddysearch.presentation.mvp.view.impl.LoginViewImpl;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> {
    @Override
    protected LoginView initView() {
        return new LoginViewImpl(this);
    }
}
