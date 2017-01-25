package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.domain.interactor.user.GetUsers;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultObserver;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.ViewScope;
import com.buddysearch.android.presentation.mapper.UserDtoModelMapper;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.view.UsersView;
import com.google.firebase.messaging.FirebaseMessaging;

import org.reactivestreams.Subscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import lombok.Getter;

@ViewScope
public class UsersPresenter extends BasePresenter<UsersView> {

    private GetUsers getUsers;

    private GetUser getUser;

    private UserDtoModelMapper userDtoModelMapper;

    private AuthManager authManager;

    private DisposableObserver<String> signOutObserver;

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
        getUsers.dispose();
        getUser.dispose();

        if (signOutObserver != null) {
            signOutObserver.dispose();
            signOutObserver = null;
        }
    }

    @Override
    public void refreshData() {
        retrieveCurrentUser();
        retrieveUsers();
    }

    private void retrieveUsers() {
        view.showProgress();
        getUsers.execute(new DefaultObserver<List<UserDto>>(view) {
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

        DisposableObserver<UserDto> getUserSubscriber = new DefaultObserver<UserDto>(view) {

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

        getUser.setOnUserChangedListener(userId -> {
            if (userId.equals(authManager.getCurrentUserId())) {
                getUser.execute(authManager.getCurrentUserId(), getUserSubscriber);
            }
        });
    }

    public void signOut() {
        view.showProgress(R.string.signing_out);

        signOutObserver = new DefaultObserver<String>(view) {
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
        authManager.signOut(signOutObserver);
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
