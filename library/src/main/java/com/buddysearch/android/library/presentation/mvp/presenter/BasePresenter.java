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

    /**
     * Use this to define data source for presenter:
     * - repository (server or storage)
     * - presenter (from presenter cache)
     */
    public enum LoadDataType {
        FROM_REPOSITORY, FROM_PRESENTER
    }

    public void attachView(@NonNull VIEW view) {
        this.view = view;
        onViewAttached();
        networkManager.add(toString(), () -> refreshData(LoadDataType.FROM_PRESENTER));
    }

    public void detachView() {
        networkManager.remove(toString());
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

    public abstract void refreshData(LoadDataType loadDataType);

    protected void onViewAttached() {
    }

    protected void onViewDetached() {
    }

    protected void onDestroyed() {
    }
}
