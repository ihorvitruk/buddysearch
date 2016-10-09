package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultSubscriber;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.mvp.view.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

import rx.Subscriber;

public class LoginPresenter extends BasePresenter<LoginView> {

    private AuthManager authManager;

    private Subscriber<String> signInSubscriber;

    @Inject
    public LoginPresenter(NetworkManager networkManager, AuthManager authManager) {
        super(networkManager);
        this.authManager = authManager;
    }

    @Override
    public void refreshData() {
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
        if (signInSubscriber != null) {
            signInSubscriber.unsubscribe();
            signInSubscriber = null;
        }
    }

    public void signInWithGoogle(GoogleSignInAccount googleSignInAccount) {
        signInSubscriber = new DefaultSubscriber<String>(view) {
            @Override
            public void onNext(String s) {
                super.onNext(s);
                view.navigateToUsers();
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(R.string.authentication_failed);
                view.hideProgress();
            }
        };
        authManager.signInGoogle(googleSignInAccount, signInSubscriber);
    }
}
