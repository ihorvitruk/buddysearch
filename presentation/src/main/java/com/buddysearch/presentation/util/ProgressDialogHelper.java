package com.buddysearch.presentation.util;

import android.app.ProgressDialog;
import android.content.Context;

import lombok.Getter;

public class ProgressDialogHelper {

    @Getter
    private ProgressDialog dialog;

    public void showProgress(Context context) {
        showProgress(context, null);
    }

    public void showProgress(Context context, String message) {
        showProgress(context, message, null);
    }

    public void showProgress(Context context, String message, String title) {
        if (context == null) {
            return;
        }

        hideProgress();

        dialog = new ProgressDialog(context);
        if (message != null) dialog.setMessage(message);
        if (title != null) dialog.setTitle(title);
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void hideProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
