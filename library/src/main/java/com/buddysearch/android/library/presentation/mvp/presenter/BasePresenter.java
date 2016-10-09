package com.buddysearch.android.library.presentation.mvp.presenter;

import android.support.annotation.NonNull;

import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.mvp.view.View;

public abstract class BasePresenter<VIEW extends View> {

    protected NetworkManager networkManager;

    public BasePresenter(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    protected VIEW view;

    public void attachView(@NonNull VIEW view) {
        this.view = view;
        onViewAttached();
        networkManager.add(getClass().getName(), this::refreshData);
    }

    public void detachView() {
        networkManager.remove(getClass().getName());
        onViewDetached();
        this.view = null;
    }

    public void resume() {
    }

    public void pause() {
    }

    public void destroy() {
        onDestroyed();
    }

    public abstract void refreshData();

    protected void onViewAttached() {
    }

    protected void onViewDetached() {
    }

    protected void onDestroyed() {
    }
}
