package com.buddysearch.presentation.mvp.presenter;

import android.support.annotation.NonNull;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.NetworkManager;
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
        networkManager.register(this::refreshData);
        onViewAttached();
    }

    public void detachView() {
        networkManager.unregister();
        onViewDetached();
    }

    public void resume() {
    }

    public void pause() {
        view.hideProgress();
    }

    public abstract void refreshData();

    protected void onViewAttached() {
    }

    protected void onViewDetached() {
    }
}
