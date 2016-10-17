package com.buddysearch.android.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivitySplashBinding;
import com.buddysearch.android.presentation.di.component.ViewComponent;
import com.buddysearch.android.presentation.mvp.presenter.SplashPresenter;
import com.buddysearch.android.presentation.mvp.view.SplashView;
import com.buddysearch.android.presentation.mvp.view.impl.SplashViewImpl;

import javax.inject.Inject;

import dagger.Lazy;

public class SplashActivity extends BaseDaggerActivity<SplashView, SplashPresenter, ActivitySplashBinding> {

    @Inject
    Lazy<SplashPresenter> splashPresenter;

    public static void start(Context context) {
        Intent intent = BaseActivity.getBaseStartIntent(context, SplashActivity.class, true);
        context.startActivity(intent);
    }

    @Override
    public void onLoadFinished() {
        super.onLoadFinished();
        initUi();
    }

    @Override
    protected SplashView initView() {
        return new SplashViewImpl(this) {

            @Override
            public void navigateToLogin() {
                LoginActivity.start(SplashActivity.this);
                finish();
            }

            @Override
            public void navigateToUsers() {
                UsersActivity.start(SplashActivity.this);
                finish();
            }
        };
    }

    @Override
    protected Lazy<SplashPresenter> initPresenter() {
        return splashPresenter;
    }

    @Override
    protected ActivitySplashBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    @Override
    protected void injectViewComponent(ViewComponent viewComponent) {
        viewComponent.inject(this);
    }

    private void initUi() {
        initSwipeToRefresh();
    }

    private void initSwipeToRefresh() {
        ((ViewImpl) view).initSwipeToRefresh(binding.swipeToRefresh, presenter);
    }
}
