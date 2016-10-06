package com.buddysearch.android.library.presentation.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.library.presentation.mvp.view.View;
import com.buddysearch.android.library.presentation.ui.loader.PresenterLoader;

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
        presenter = initPresenter();
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
                return BaseActivity.this.initPresenter();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<PRESENTER> loader, PRESENTER presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoaderReset(Loader<PRESENTER> loader) {
        presenter = null;
    }

    public VIEW getView() {
        return view;
    }

    protected abstract VIEW initView();

    protected abstract PRESENTER initPresenter();

    protected abstract BINDING initBinding();
}
