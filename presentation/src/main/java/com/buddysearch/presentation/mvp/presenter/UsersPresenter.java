package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.domain.dto.UserDto;
import com.buddysearch.presentation.domain.interactor.DefaultSubscriber;
import com.buddysearch.presentation.domain.interactor.GetUser;
import com.buddysearch.presentation.domain.interactor.GetUsers;
import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mapper.UserModelMapper;
import com.buddysearch.presentation.mvp.view.UsersView;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UsersPresenter extends BasePresenter<UsersView> {

    private GetUsers getUsers;

    private GetUser getUser;

    private UserModelMapper userModelMapper;

    @Inject
    public UsersPresenter(NetworkManager networkManager, AuthManager authManager,
                          GetUsers getUsers, GetUser getUser, UserModelMapper userModelMapper) {
        super(networkManager, authManager);
        this.getUsers = getUsers;
        this.getUser = getUser;
        this.userModelMapper = userModelMapper;
    }

    @Override
    protected void onViewAttached() {
        refreshData();
    }

    @Override
    protected void onViewDetached() {
        getUsers.unsubscribe();
    }

    @Override
    public void refreshData() {
        getCurrentUser();
    }

    private void getUsers() {
        getUsers.execute(new DefaultSubscriber<List<UserDto>>() {
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
        getUser.execute(authManager.getCurrentUserId(), new DefaultSubscriber<UserDto>() {
            @Override
            public void onNext(UserDto user) {
                super.onNext(user);
                view.renderCurrentUser(userModelMapper.map(user));
                getUsers();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(e.getMessage());
                view.hideProgress();
            }
        });
    }
}
