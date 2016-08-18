package com.buddysearch.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.buddysearch.presentation.model.UserModel;
import com.buddysearch.presentation.presenter.SplashPresenter;
import com.buddysearch.presentation.view.SplashView;
import com.buddysearch.presentation.view.impl.SplashViewImpl;

import java.util.List;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected SplashView initView() {
        return new SplashViewImpl(this) {
            @Override
            public void renderUsers(List<UserModel> users) {

            }
        };
    }
}
