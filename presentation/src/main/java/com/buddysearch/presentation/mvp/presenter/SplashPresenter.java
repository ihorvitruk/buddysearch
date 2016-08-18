package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.R;
import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.SplashView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    public SplashPresenter(NetworkManager networkManager) {
        super(networkManager);
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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                view.navigateToLogin();
            } else {
                view.navigateToUsers();
            }
        } else {
            view.showMessage(R.string.no_internet_connection);
        }
    }
}
