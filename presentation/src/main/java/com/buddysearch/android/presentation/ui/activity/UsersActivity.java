package com.buddysearch.android.presentation.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivityUsersBinding;
import com.buddysearch.android.presentation.di.component.ActivityComponent;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUsersRecyclerView();
    }

    @Override
    public void onLoadFinished() {
        super.onLoadFinished();
        initSwipeToRefresh();
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
                Intent intent = new Intent(UsersActivity.this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void navigateToDialog(String peerId) {
                DialogActivity.start(UsersActivity.this, peerId);
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
    protected void injectActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
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
