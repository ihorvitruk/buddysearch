package com.buddysearch.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.buddysearch.presentation.util.ProgressDialogHelper;

public abstract class BaseView {

    private Activity activity;

    private Fragment fragment;

    private ProgressDialogHelper progressDialogHelper;

    public BaseView(Activity activity) {
        this.activity = activity;
        init();
    }

    public BaseView(Fragment fragment) {
        this.fragment = fragment;
        init();
    }

    private void init() {
        progressDialogHelper = new ProgressDialogHelper();
    }

    public void showMessage(int resId) {
        Snackbar.make(getSnackBarBackground(), resId, Snackbar.LENGTH_SHORT).show();
    }

    public void showMessage(String message) {
        if (message != null) {
            Snackbar.make(getSnackBarBackground(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    public void showProgress() {
        progressDialogHelper.showProgress(getContext());
    }

    public void showProgress(int messageStringId) {
        progressDialogHelper.showProgress(getContext(), messageStringId);
    }

    public void showProgress(int messageStringId, int titleStringId) {
        progressDialogHelper.showProgress(getContext(), messageStringId, titleStringId);
    }

    public void hideProgress() {
        progressDialogHelper.hideProgress();
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
