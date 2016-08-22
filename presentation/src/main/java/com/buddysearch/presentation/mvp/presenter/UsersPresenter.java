package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.GetUser;
import com.buddysearch.android.domain.interactor.GetUsers;
import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.mapper.UserModelMapper;
import com.buddysearch.presentation.mvp.view.UsersView;
import com.buddysearch.presentation.util.DefaultSubscriber;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UsersPresenter extends BasePresenter<UsersView, UserCache> {

    private GetUsers getUsers;

    private GetUser getUser;

    private UserModelMapper userModelMapper;

    @Inject
    public UsersPresenter(NetworkManager networkManager, AuthManager authManager,
                          GetUsers getUsers, GetUser getUser, UserModelMapper userModelMapper) {
        super(networkManager, authManager);
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
        getUsers.unsubscribe();
    }

    @Override
    protected UserCache initCache() {
        return null;
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
        authManager.signOut(new AuthManager.SignOutCallback() {
            @Override
            public void onSignOutSuccess() {
                view.hideProgress();
                view.navigateToSplash();
            }

            @Override
            public void onSignOutError() {
                view.hideProgress();
                view.showMessage("Sign out error occurred");
            }
        });
    }
}
