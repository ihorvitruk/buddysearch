package com.buddysearch.presentation.mvp.view.impl;

import com.buddysearch.presentation.mvp.view.DialogView;
import com.buddysearch.presentation.ui.activity.BaseActivity;

public abstract class DialogViewImpl extends ViewImpl implements DialogView {
    public DialogViewImpl(BaseActivity activity) {
        super(activity);
    }
}
