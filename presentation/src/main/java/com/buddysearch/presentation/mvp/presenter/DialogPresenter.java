package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.DialogView;

public class DialogPresenter extends BasePresenter<DialogView> {

    public DialogPresenter(NetworkManager networkManager) {
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
