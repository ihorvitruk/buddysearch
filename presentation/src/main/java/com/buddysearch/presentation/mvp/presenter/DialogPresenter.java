package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.android.data.store.cache.Cache;
import com.buddysearch.presentation.mvp.view.DialogView;

import javax.inject.Inject;

public class DialogPresenter extends BasePresenter<DialogView, Cache> {

    @Inject
    public DialogPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager, authManager);
    }

    @Override
    protected Cache initCache() {
        return null;
    }

    @Override
    public void refreshData() {
    }
}
