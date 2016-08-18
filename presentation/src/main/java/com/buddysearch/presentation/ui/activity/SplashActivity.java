package com.buddysearch.presentation.ui.activity;

import android.content.Intent;

import com.buddysearch.presentation.mvp.presenter.SplashPresenter;
import com.buddysearch.presentation.mvp.view.SplashView;
import com.buddysearch.presentation.mvp.view.impl.SplashViewImpl;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected SplashView initView() {
        return new SplashViewImpl(this) {

            @Override
            public void navigateToLogin() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void navigateToUsers() {
                startActivity(new Intent(SplashActivity.this, UsersActivity.class));
                finish();
            }
        };
    }

    @Override
    protected SplashPresenter initPresenter() {
        return splashPresenter;
    }
}
