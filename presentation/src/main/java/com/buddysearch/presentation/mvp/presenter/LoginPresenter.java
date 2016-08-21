package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.presentation.cache.Cache;
import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.presentation.mvp.view.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView, Cache> {

    @Inject
    public LoginPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager, authManager);
    }

    @Override
    protected Cache initCache() {
        return null;
    }

    @Override
    public void refreshData() {
    }

    public void signInWithGoogle(GoogleSignInAccount googleSignInAccount) {
        authManager.signInGoogle(googleSignInAccount, new AuthManager.SignInCallback() {
            @Override
            public void onSignInSuccess() {
                view.navigateToUsers();
                view.hideProgress();
            }

            @Override
            public void onSignInError() {
                view.showMessage("Authentication failed.");
                view.hideProgress();
            }
        });
    }
}
