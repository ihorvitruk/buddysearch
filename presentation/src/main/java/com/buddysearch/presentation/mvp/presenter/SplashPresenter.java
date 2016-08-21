package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.cache.Cache;
import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.SplashView;

import javax.inject.Inject;

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashView, Cache> {

    @Inject
    public SplashPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager, authManager);
    }

    @Override
    protected Cache initCache() {
        return null;
    }

    @Override
    public void refreshData() {
        chooseNavigation();
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        refreshData();
    }

    private void chooseNavigation() {
        if (authManager.isSignedIn()) {
            view.navigateToUsers();

        } else {
            view.navigateToLogin();
        }
    }
}
