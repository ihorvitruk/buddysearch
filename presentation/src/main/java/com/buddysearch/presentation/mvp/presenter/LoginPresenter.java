package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.LoginView;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    public LoginPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager, authManager);
    }

    @Override
    protected void onViewAttached() {
    }

    @Override
    protected void onViewDetached() {
    }

    @Override
    public void refreshData() {
    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}
