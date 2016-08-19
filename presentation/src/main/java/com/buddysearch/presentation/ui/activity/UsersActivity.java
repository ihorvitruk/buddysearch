package com.buddysearch.presentation.ui.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.buddysearch.presentation.R;
import com.buddysearch.presentation.mvp.model.UserModel;
import com.buddysearch.presentation.mvp.presenter.UsersPresenter;
import com.buddysearch.presentation.mvp.view.UsersView;
import com.buddysearch.presentation.mvp.view.impl.UsersViewImpl;

import java.util.List;

import javax.inject.Inject;

public class UsersActivity extends BaseActivity<UsersView, UsersPresenter> {

    @Inject
    UsersPresenter usersPresenter;

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
                TextView textView = (TextView) findViewById(R.id.tv_username);
                textView.setText(user.getFirstName() + " " + user.getLastName());
            }

            @Override
            public void renderUsers(List<UserModel> users) {

            }
        };
    }

    @Override
    protected UsersPresenter initPresenter() {
        getActivityComponent().inject(this);
        return usersPresenter;
    }
}
