package com.buddysearch.presentation.ui.activity;

import android.content.Intent;

import com.buddysearch.presentation.mvp.presenter.SplashPresenter;
import com.buddysearch.presentation.mvp.view.SplashView;
import com.buddysearch.presentation.mvp.view.impl.SplashViewImpl;
import com.buddysearch.presentation.other.LoginActivity;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> {

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
}
