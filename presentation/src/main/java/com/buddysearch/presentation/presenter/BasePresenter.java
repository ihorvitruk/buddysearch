package com.buddysearch.presentation.presenter;

import android.support.annotation.NonNull;

import com.buddysearch.presentation.view.View;

public abstract class BasePresenter<VIEW extends View> {

    protected VIEW view;

    public void attachView(@NonNull VIEW view) {
        this.view = view;
        onViewAttached();
    }

    public void detachView() {
        onViewDetached();
    }

    protected abstract void onViewAttached();

    protected abstract void onViewDetached();

    public abstract void refreshData();
}
