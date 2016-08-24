package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.android.data.store.cache.Cache;
import com.buddysearch.presentation.mvp.view.LoginView;
import com.buddysearch.presentation.util.DefaultSubscriber;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    public LoginPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager, authManager);
    }

    @Override
    public void refreshData() {
    }

    public void signInWithGoogle(GoogleSignInAccount googleSignInAccount) {
        authManager.signInGoogle(googleSignInAccount, new DefaultSubscriber<String>(view) {

            @Override
            public void onNext(String s) {
                super.onNext(s);
                view.navigateToUsers();
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage("Authentication failed.");
                view.hideProgress();
            }
        });
    }
}
