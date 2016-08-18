package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(NetworkManager networkManager) {
        super(networkManager);
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
}
