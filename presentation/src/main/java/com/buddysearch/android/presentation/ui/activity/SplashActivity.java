package com.buddysearch.android.presentation.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivitySplashBinding;
import com.buddysearch.android.presentation.mvp.presenter.SplashPresenter;
import com.buddysearch.android.presentation.mvp.view.SplashView;
import com.buddysearch.android.presentation.mvp.view.impl.SplashViewImpl;
import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;

import javax.inject.Inject;

public class SplashActivity extends BaseDaggerActivity<SplashView, SplashPresenter, ActivitySplashBinding> {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipeToRefresh();
    }

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
        getActivityComponent().inject(this);
        return splashPresenter;
    }

    @Override
    protected ActivitySplashBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    private void initSwipeToRefresh() {
        ((ViewImpl) view).initSwipeToRefresh(binding.swipeToRefresh, presenter);
    }
}
