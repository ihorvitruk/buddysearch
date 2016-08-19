package com.buddysearch.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.buddysearch.presentation.App;
import com.buddysearch.presentation.di.component.ActivityComponent;
import com.buddysearch.presentation.di.module.ActivityModule;
import com.buddysearch.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.presentation.mvp.view.View;

public abstract class BaseActivity<VIEW extends View, PRESENTER extends BasePresenter<VIEW>> extends AppCompatActivity {

    protected PRESENTER presenter;

    protected VIEW view;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        view = initView();
        presenter = initPresenter();
        presenter.attachView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    protected abstract VIEW initView();

    protected abstract PRESENTER initPresenter();

    private void initActivityComponent() {
        activityComponent = ((App) getApplication()).getAppComponent().plus(new ActivityModule(this));
    }
}
