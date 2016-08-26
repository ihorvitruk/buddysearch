package com.buddysearch.android.presentation.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.library.presentation.mvp.view.View;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.App;
import com.buddysearch.android.presentation.di.component.ActivityComponent;
import com.buddysearch.android.presentation.di.module.ActivityModule;
import com.buddysearch.android.presentation.di.module.CacheModule;
import com.buddysearch.android.presentation.di.module.EntityStoreModule;
import com.buddysearch.android.presentation.di.module.RepositoryModule;

public abstract class BaseDaggerActivity<VIEW extends View,
        PRESENTER extends BasePresenter,
        BINDING extends ViewDataBinding> extends BaseActivity<VIEW, PRESENTER, BINDING> {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initActivityComponent();
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    private void initActivityComponent() {
        activityComponent = ((App) getApplication()).getAppComponent()
                .plus(new ActivityModule(this),
                        new RepositoryModule(),
                        new EntityStoreModule(),
                        new CacheModule());
    }
}
