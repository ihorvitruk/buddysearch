package com.buddysearch.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultSubscriber;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.presentation.mvp.view.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {

    private AuthManager authManager;

    @Inject
    public LoginPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager);
        this.authManager = authManager;
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
