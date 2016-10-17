package com.buddysearch.android.library.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.library.presentation.mvp.view.View;
import com.buddysearch.android.library.presentation.ui.loader.PresenterLoader;

import dagger.Lazy;

public abstract class BaseActivity<VIEW extends View,
        PRESENTER extends BasePresenter,
        BINDING extends ViewDataBinding> extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<PRESENTER> {

    private static final int LOADER_ID = 101;

    protected PRESENTER presenter;

    protected VIEW view;

    protected BINDING binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initBinding();
        view = initView();

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        presenter.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @Override
    public Loader<PRESENTER> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<PRESENTER>(this) {

            @Override
            protected PRESENTER initPresenter() {
                return BaseActivity.this.initPresenter().get();
            }
        };
    }

    @Override
    public final void onLoadFinished(Loader<PRESENTER> loader, PRESENTER presenter) {
        this.presenter = presenter;
        onLoadFinished();
    }

    public void onLoadFinished() {
    }

    @Override
    public final void onLoaderReset(Loader<PRESENTER> loader) {
        onLoadReset();
        presenter = null;
    }

    public void onLoadReset() {
    }

    public VIEW getView() {
        return view;
    }

    protected abstract VIEW initView();

    protected abstract Lazy<PRESENTER> initPresenter();

    protected abstract BINDING initBinding();

    protected static Intent getBaseStartIntent(Context context, Class<? extends BaseActivity> activityClass, boolean clearStack) {
        Intent intent = new Intent(context, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (clearStack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
    }
}
