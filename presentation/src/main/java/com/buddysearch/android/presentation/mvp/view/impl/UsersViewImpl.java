package com.buddysearch.android.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.mvp.view.UsersView;

public abstract class UsersViewImpl extends ViewImpl implements UsersView {
    public UsersViewImpl(BaseActivity activity) {
        super(activity);
    }
}
