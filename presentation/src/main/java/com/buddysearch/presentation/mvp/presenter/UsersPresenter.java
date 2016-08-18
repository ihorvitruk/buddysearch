package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.domain.interactor.DefaultSubscriber;
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

    private UserModelMapper userModelMapper;

    @Inject
    public UsersPresenter(NetworkManager networkManager, AuthManager authManager,
                          GetUsers getUsers, UserModelMapper userModelMapper) {
        super(networkManager, authManager);
        this.getUsers = getUsers;
        this.userModelMapper = userModelMapper;
    }

    @Override
    protected void onViewAttached() {
        view.renderCurrentUser(authManager.getCurrentUser());
        refreshData();
    }

    @Override
    protected void onViewDetached() {
        getUsers.unsubscribe();
    }

    @Override
    public void refreshData() {
        getUsers();
    }

    private void getUsers() {
        view.showProgress();
        getUsers.execute(null, new DefaultSubscriber<List<User>>() {
            @Override
            public void onNext(List<User> users) {
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
}
