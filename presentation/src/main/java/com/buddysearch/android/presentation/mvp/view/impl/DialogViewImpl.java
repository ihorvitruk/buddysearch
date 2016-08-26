package com.buddysearch.android.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.presentation.mvp.view.DialogView;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;

public abstract class DialogViewImpl extends ViewImpl implements DialogView {
    public DialogViewImpl(BaseActivity activity) {
        super(activity);
    }
}
