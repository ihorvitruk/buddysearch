package com.buddysearch.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.buddysearch.presentation.mvp.model.UserModel;
import com.buddysearch.presentation.mvp.presenter.UsersPresenter;
import com.buddysearch.presentation.mvp.view.UsersView;
import com.buddysearch.presentation.mvp.view.impl.UsersViewImpl;

import java.util.List;

public class UsersActivity extends BaseActivity<UsersView, UsersPresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected UsersView initView() {
        return new UsersViewImpl(this) {
            @Override
            public void renderUsers(List<UserModel> users) {

            }
        };
    }
}
