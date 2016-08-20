package com.buddysearch.presentation.mvp.presenter;

import android.support.annotation.NonNull;

import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.View;

public abstract class BasePresenter<VIEW extends View> {

    protected NetworkManager networkManager;

    protected AuthManager authManager;

    public BasePresenter(NetworkManager networkManager, AuthManager authManager) {
        this.networkManager = networkManager;
        this.authManager = authManager;
    }

    protected VIEW view;

    public void attachView(@NonNull VIEW view) {
        this.view = view;
        onViewAttached();
    }

    public void detachView() {
        onViewDetached();
    }

    public void resume() {
    }

    public void pause() {
        view.hideProgress();
    }

    protected abstract void onViewAttached();

    protected abstract void onViewDetached();

    public abstract void refreshData();

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
