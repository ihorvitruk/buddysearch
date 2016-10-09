package com.buddysearch.android.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.mvp.view.DialogView;

public abstract class DialogViewImpl extends ViewImpl implements DialogView {
    public DialogViewImpl(BaseActivity activity) {
        super(activity);
    }
}
