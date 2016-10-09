package com.buddysearch.android.library.presentation.mvp.view.impl;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.buddysearch.android.library.R;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.library.presentation.util.ProgressDialogHelper;

public abstract class ViewImpl implements com.buddysearch.android.library.presentation.mvp.view.View {

    private Activity activity;

    private Fragment fragment;

    private ProgressDialogHelper progressDialogHelper;

    public ViewImpl(Activity activity) {
        this.activity = activity;
        init();
    }

    public ViewImpl(Fragment fragment) {
        this.fragment = fragment;
        init();
    }

    private void init() {
        progressDialogHelper = new ProgressDialogHelper();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getSnackBarBackground(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int messageResId) {
        showMessage(getContext().getString(messageResId));
    }

    @Override
    public void showProgress() {
        progressDialogHelper.showProgress(getContext(), getContext().getString(R.string.loading));
    }

    @Override
    public void showProgress(String message) {
        progressDialogHelper.showProgress(getContext(), message);
    }

    @Override
    public void showProgress(int messageResId) {
        showProgress(getContext().getString(messageResId));
    }

    @Override
    public void showProgress(String message, String title) {
        progressDialogHelper.showProgress(getContext(), message, title);
    }

    @Override
    public void showProgress(int messageResId, int titleResId) {
        showProgress(getContext().getString(messageResId), getContext().getString(titleResId));
    }

    @Override
    public void hideProgress() {
        progressDialogHelper.hideProgress();
    }

    public void initSwipeToRefresh(SwipeRefreshLayout swipeRefreshLayout, BasePresenter presenter) {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            presenter.refreshData();
        });
    }

    private Context getContext() {
        if (activity != null) {
            return activity;
        } else if (fragment != null) {
            return fragment.getContext();
        }
        return null;
    }

    private View getSnackBarBackground() {
        if (activity != null) {
            return activity.findViewById(android.R.id.content);
        } else if (fragment != null) {
            return fragment.getView();
        }
        return null;
    }
}
