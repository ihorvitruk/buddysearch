package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.domain.interactor.user.GetUsers;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultSubscriber;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.ViewScope;
import com.buddysearch.android.presentation.mapper.UserDtoModelMapper;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.view.UsersView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import rx.Subscriber;

@ViewScope
public class UsersPresenter extends BasePresenter<UsersView> {

    private GetUsers getUsers;

    private GetUser getUser;

    private UserDtoModelMapper userDtoModelMapper;

    private AuthManager authManager;

    private Subscriber<String> signOutSubscriber;

    @Getter
    private UserModel currentUser;

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

        if (signOutSubscriber != null) {
            signOutSubscriber.unsubscribe();
            signOutSubscriber = null;
        }
    }

    @Override
    public void refreshData() {
        retrieveCurrentUser();
        retrieveUsers();
    }

    private void retrieveUsers() {
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

    private void retrieveCurrentUser() {
        view.showProgress();

        Subscriber<UserDto> getUserSubscriber = new DefaultSubscriber<UserDto>(view) {

            @Override
            public void onNext(UserDto user) {
                super.onNext(user);
                currentUser = userDtoModelMapper.map2(user);
                view.renderCurrentUser(currentUser);
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(e.getMessage());
                view.hideProgress();
            }
        };

        getUser.execute(authManager.getCurrentUserId(), getUserSubscriber);

        getUser.setOnUserChanged(userId -> {
            if (userId.equals(authManager.getCurrentUserId())) {
                getUser.execute(authManager.getCurrentUserId(), getUserSubscriber);
            }
        });
    }

    public void signOut() {
        view.showProgress(R.string.signing_out);

        signOutSubscriber = new DefaultSubscriber<String>(view) {
            @Override
            public void onNext(String userId) {
                super.onNext(userId);
                view.hideProgress();
                view.navigateToSplash();
                FirebaseMessaging.getInstance().unsubscribeFromTopic("user_" + userId);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(R.string.sign_out_error);
                view.hideProgress();
            }
        };
        authManager.signOut(signOutSubscriber);
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
