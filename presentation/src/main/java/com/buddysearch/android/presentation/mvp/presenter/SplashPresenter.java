package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.di.scope.ActivityScope;
import com.buddysearch.android.presentation.mvp.view.SplashView;

import javax.inject.Inject;

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashView> {

    private AuthManager authManager;

    @Inject
    public SplashPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager);
        this.authManager = authManager;
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
