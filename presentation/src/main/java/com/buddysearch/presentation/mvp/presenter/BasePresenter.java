package com.buddysearch.presentation.mvp.presenter;

import android.support.annotation.NonNull;

import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.View;

public abstract class BasePresenter<VIEW extends View> {

    protected NetworkManager networkManager;

    public BasePresenter(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    protected VIEW view;

    public void attachView(@NonNull VIEW view) {
        this.view = view;
        onViewAttached();
    }

    public void detachView() {
        this.view = null;
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
}
