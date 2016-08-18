package com.buddysearch.presentation.mvp.view.impl;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.buddysearch.presentation.ui.activity.BaseActivity;
import com.buddysearch.presentation.ui.activity.SplashActivity;
import com.buddysearch.presentation.util.ProgressDialogHelper;

public abstract class ViewImpl implements com.buddysearch.presentation.mvp.view.View {

    private BaseActivity activity;

    private Fragment fragment;

    private ProgressDialogHelper progressDialogHelper;

    public ViewImpl(BaseActivity activity) {
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
        progressDialogHelper.showProgress(getContext());
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

    @Override
    public void navigateToSplash() {
        Context context = getContext();
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
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
