package com.buddysearch.presentation.mvp.presenter;

import android.support.annotation.NonNull;

import com.buddysearch.presentation.cache.Cache;
import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.View;

public abstract class BasePresenter<VIEW extends View, CACHE extends Cache> {

    protected NetworkManager networkManager;

    protected AuthManager authManager;

    protected CACHE cache;

    public BasePresenter(NetworkManager networkManager, AuthManager authManager) {
        this.networkManager = networkManager;
        this.authManager = authManager;
        this.cache = initCache();
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

    protected abstract CACHE initCache();

    public abstract void refreshData();

    protected void onViewAttached() {
    }

    protected void onViewDetached() {
    }
}
