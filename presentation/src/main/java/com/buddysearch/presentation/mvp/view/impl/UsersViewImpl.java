package com.buddysearch.presentation.mvp.view.impl;

import com.buddysearch.presentation.mvp.view.UsersView;
import com.buddysearch.presentation.ui.activity.BaseActivity;

public abstract class UsersViewImpl extends ViewImpl implements UsersView {
    public UsersViewImpl(BaseActivity activity) {
        super(activity);
    }
}
