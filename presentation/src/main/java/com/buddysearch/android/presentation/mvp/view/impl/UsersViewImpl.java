package com.buddysearch.android.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.presentation.mvp.view.UsersView;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;

public abstract class UsersViewImpl extends ViewImpl implements UsersView {
    public UsersViewImpl(BaseActivity activity) {
        super(activity);
    }
}
