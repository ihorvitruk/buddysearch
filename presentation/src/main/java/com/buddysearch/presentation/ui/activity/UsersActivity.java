package com.buddysearch.presentation.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.presentation.R;
import com.buddysearch.presentation.databinding.ActivityUsersBinding;
import com.buddysearch.presentation.mvp.model.UserModel;
import com.buddysearch.presentation.mvp.presenter.UsersPresenter;
import com.buddysearch.presentation.mvp.view.UsersView;
import com.buddysearch.presentation.mvp.view.impl.UsersViewImpl;
import com.buddysearch.presentation.ui.adapter.UsersAdapter;

import java.util.List;

import javax.inject.Inject;

public class UsersActivity extends BaseDaggerActivity<UsersView, UsersPresenter, ActivityUsersBinding> {

    @Inject
    UsersPresenter usersPresenter;

    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUsersRecyclerView();
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
        };
    }

    @Override
    protected UsersPresenter initPresenter() {
        getActivityComponent().inject(this);
        return usersPresenter;
    }

    @Override
    protected ActivityUsersBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_users);
    }

    private void initUsersRecyclerView() {
        usersAdapter = new UsersAdapter();
        binding.rvUsers.setAdapter(usersAdapter);
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSwipeToRefresh() {
        ((ViewImpl) view).initSwipeToRefresh(binding.swipeToRefresh, presenter);
    }
}
