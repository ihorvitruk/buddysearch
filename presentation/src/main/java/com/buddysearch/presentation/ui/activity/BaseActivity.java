package com.buddysearch.presentation.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.buddysearch.presentation.App;
import com.buddysearch.presentation.di.component.ActivityComponent;
import com.buddysearch.presentation.di.module.ActivityModule;
import com.buddysearch.presentation.di.module.CacheModule;
import com.buddysearch.presentation.di.module.EntityStoreModule;
import com.buddysearch.presentation.di.module.RepositoryModule;
import com.buddysearch.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.presentation.mvp.view.View;

public abstract class BaseActivity<VIEW extends View,
        PRESENTER extends BasePresenter,
        BINDING extends ViewDataBinding> extends AppCompatActivity {

    protected PRESENTER presenter;

    protected VIEW view;

    protected BINDING binding;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initBinding();
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

    public VIEW getView() {
        return view;
    }

    protected abstract VIEW initView();

    protected abstract PRESENTER initPresenter();

    protected abstract BINDING initBinding();

    private void initActivityComponent() {
        activityComponent = ((App) getApplication()).getAppComponent()
                .plus(new ActivityModule(this),
                        new RepositoryModule(),
                        new EntityStoreModule(),
                        new CacheModule(this));
    }
}
