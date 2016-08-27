package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.domain.interactor.user.GetUsers;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultSubscriber;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.ActivityScope;
import com.buddysearch.android.presentation.mapper.UserDtoModelMapper;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.view.UsersView;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UsersPresenter extends BasePresenter<UsersView> {

    private GetUsers getUsers;

    private GetUser getUser;

    private UserDtoModelMapper userDtoModelMapper;

    private AuthManager authManager;

    @Inject
    public UsersPresenter(NetworkManager networkManager, AuthManager authManager,
                          GetUsers getUsers, GetUser getUser, UserDtoModelMapper userDtoModelMapper) {
        super(networkManager);
        this.authManager = authManager;
        this.getUsers = getUsers;
        this.getUser = getUser;
        this.userDtoModelMapper = userDtoModelMapper;
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
        getUser.unsubscribe();
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
                view.renderUsers(excludeCurrent(userDtoModelMapper.map2(users)));
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
                view.renderCurrentUser(userDtoModelMapper.map2(user));
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
        view.showProgress(R.string.signing_out);
        authManager.signOut(new DefaultSubscriber<String>(view) {

            @Override
            public void onNext(String s) {
                super.onNext(s);
                view.navigateToSplash();
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(R.string.sign_out_error);
                view.hideProgress();
            }
        });
    }

    private List<UserModel> excludeCurrent(List<UserModel> users) {
        for (UserModel user : users) {
            if (user.getId().equals(authManager.getCurrentUserId())) {
                users.remove(user);
                break;
            }
        }
        return users;
    }
}
