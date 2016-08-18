package com.buddysearch.presentation.mvp.view.impl;

import android.app.Activity;

import com.buddysearch.presentation.mvp.model.UserModel;
import com.buddysearch.presentation.mvp.view.UsersView;

import java.util.List;

public class UsersViewImpl extends ViewImpl implements UsersView {
    public UsersViewImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void renderUsers(List<UserModel> users) {

    }
}
