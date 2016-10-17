package com.buddysearch.android.presentation.mvp.view.impl;

import android.app.Activity;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.presentation.mvp.view.EditProfileView;

public abstract class EditProfileViewImpl extends ViewImpl implements EditProfileView {
    public EditProfileViewImpl(Activity activity) {
        super(activity);
    }
}
