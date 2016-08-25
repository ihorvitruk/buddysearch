package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.presentation.mvp.view.DialogView;

import javax.inject.Inject;

public class DialogPresenter extends BasePresenter<DialogView> {

    @Inject
    public DialogPresenter(NetworkManager networkManager) {
        super(networkManager);
    }

    @Override
    public void refreshData() {
    }
}
