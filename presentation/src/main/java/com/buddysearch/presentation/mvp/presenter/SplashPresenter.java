package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.R;
import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.SplashView;

import javax.inject.Inject;

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    public SplashPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager, authManager);
    }

    @Override
    protected void onViewAttached() {
        refreshData();
    }

    @Override
    protected void onViewDetached() {
    }

    @Override
    public void refreshData() {
        chooseNavigation();
    }

    private void chooseNavigation() {
        if (networkManager.isNetworkAvailable()) {
            if (authManager.isSignedIn()) {
                view.navigateToUsers();

            } else {
                view.navigateToLogin();
            }
        } else {
            view.showMessage(R.string.no_internet_connection);
        }
    }
}
