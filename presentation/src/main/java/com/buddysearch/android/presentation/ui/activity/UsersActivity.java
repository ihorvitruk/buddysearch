package com.buddysearch.android.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivityUsersBinding;
import com.buddysearch.android.presentation.di.component.ViewComponent;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.presenter.UsersPresenter;
import com.buddysearch.android.presentation.mvp.view.UsersView;
import com.buddysearch.android.presentation.mvp.view.impl.UsersViewImpl;
import com.buddysearch.android.presentation.ui.adapter.UsersAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

public class UsersActivity extends BaseDaggerActivity<UsersView, UsersPresenter, ActivityUsersBinding> {

    @Inject
    Lazy<UsersPresenter> usersPresenter;

    private UsersAdapter usersAdapter;

    public static void start(Context context) {
        Intent intent = BaseActivity.getBaseStartIntent(context, UsersActivity.class, false);
        context.startActivity(intent);
    }

    @Override
    public void onLoadFinished() {
        super.onLoadFinished();
        initUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menu_logout:
                presenter.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected UsersView initView() {
        return new UsersViewImpl(this) {

            @Override
            public void renderCurrentUser(UserModel user) {
                binding.tvUsername.setText(user.getFirstName() + " " + user.getLastName());
            }

            @Override
            public void renderUsers(List<UserModel> users) {
                usersAdapter.setItems(users);
            }

            @Override
            public void navigateToSplash() {
                SplashActivity.start(UsersActivity.this);
            }

            @Override
            public void navigateToDialog(String peerId) {
                DialogActivity.start(UsersActivity.this, peerId);
            }

            @Override
            public void navigateToEditProfile() {
                EditProfileActivity.start(UsersActivity.this, presenter.getCurrentUser());
            }
        };
    }

    @Override
    protected Lazy<UsersPresenter> initPresenter() {
        return usersPresenter;
    }

    @Override
    protected ActivityUsersBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_users);
    }

    @Override
    protected void injectViewComponent(ViewComponent viewComponent) {
        viewComponent.inject(this);
    }

    private void initUi() {
        initUsersRecyclerView();
        initEditProfileButton();
        initSwipeToRefresh();
    }

    private void initEditProfileButton() {
        binding.btnEdit.setOnClickListener(view1 -> UsersActivity.this.view.navigateToEditProfile());
    }

    private void initUsersRecyclerView() {
        usersAdapter = new UsersAdapter(view);
        binding.rvUsers.setAdapter(usersAdapter);
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSwipeToRefresh() {
        ((ViewImpl) view).initSwipeToRefresh(binding.swipeToRefresh, presenter);
    }
}
