package com.buddysearch.android.presentation.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.library.presentation.mvp.view.View;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.App;
import com.buddysearch.android.presentation.di.component.ViewComponent;
import com.buddysearch.android.presentation.di.module.ViewModule;

public abstract class BaseDaggerActivity<VIEW extends View,
        PRESENTER extends BasePresenter,
        BINDING extends ViewDataBinding> extends BaseActivity<VIEW, PRESENTER, BINDING> {

    private ViewComponent viewComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initViewComponent();
        super.onCreate(savedInstanceState);
    }

    protected abstract void injectViewComponent(ViewComponent viewComponent);

    private void initViewComponent() {
        viewComponent = ((App) getApplication()).getAppComponent()
                .plus(new ViewModule(view));
        injectViewComponent(viewComponent);
    }
}
