package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.domain.interactor.user.GetUsers;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultSubscriber;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.presentation.R;
import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.mapper.UserModelMapper;
import com.buddysearch.presentation.mvp.view.UsersView;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UsersPresenter extends BasePresenter<UsersView> {

    private GetUsers getUsers;

    private GetUser getUser;

    private UserModelMapper userModelMapper;

    private AuthManager authManager;

    @Inject
    public UsersPresenter(NetworkManager networkManager, AuthManager authManager,
                          GetUsers getUsers, GetUser getUser, UserModelMapper userModelMapper) {
        super(networkManager);
        this.authManager = authManager;
        this.getUsers = getUsers;
        this.getUser = getUser;
        this.userModelMapper = userModelMapper;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        refreshData();
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
        getUsers.unsubscribe();
    }

    @Override
    public void refreshData() {
        getCurrentUser();
        getUsers();
    }

    private void getUsers() {
        view.showProgress();
        getUsers.execute(new DefaultSubscriber<List<UserDto>>(view) {
            @Override
            public void onNext(List<UserDto> users) {
                super.onNext(users);
                view.renderUsers(userModelMapper.map(users));
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(e.getMessage());
                view.hideProgress();
            }
        });
    }

    private void getCurrentUser() {
        view.showProgress();
        getUser.execute(authManager.getCurrentUserId(), new DefaultSubscriber<UserDto>(view) {

            @Override
            public void onNext(UserDto user) {
                super.onNext(user);
                view.renderCurrentUser(userModelMapper.map(user));
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(e.getMessage());
                view.hideProgress();
            }
        });
    }

    public void signOut() {
        view.showProgress();
        authManager.signOut(new DefaultSubscriber<String>(view) {

            @Override
            public void onNext(String s) {
                super.onNext(s);
                view.hideProgress();
                view.navigateToSplash();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.hideProgress();
                view.showMessage(R.string.sign_out_error);
            }
        });
    }
}
