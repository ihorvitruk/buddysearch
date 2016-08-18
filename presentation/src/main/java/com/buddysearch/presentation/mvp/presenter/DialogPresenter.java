package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.DialogView;

import javax.inject.Inject;

public class DialogPresenter extends BasePresenter<DialogView> {

    @Inject
    public DialogPresenter(NetworkManager networkManager, AuthManager authManager) {
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
}
