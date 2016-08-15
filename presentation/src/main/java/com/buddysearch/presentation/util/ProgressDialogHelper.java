package com.buddysearch.presentation.util;

import android.app.ProgressDialog;
import android.content.Context;

import lombok.Getter;

public class ProgressDialogHelper {

    @Getter
    private ProgressDialog dialog;

    private int NULL_RES = -1;

    public void showProgress(Context context) {
        showProgress(context, NULL_RES);
    }

    public void showProgress(Context context, int messageStringId) {
        showProgress(context, messageStringId, NULL_RES);
    }

    public void showProgress(Context context, int messageStringId, int titleStringId) {
        if (context == null) {
            return;
        }

        hideProgress();

        dialog = new ProgressDialog(context);
        if (messageStringId != NULL_RES) dialog.setMessage(context.getString(messageStringId));
        if (titleStringId != NULL_RES) dialog.setTitle(context.getString(titleStringId));
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
