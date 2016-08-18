package com.buddysearch.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.buddysearch.presentation.R;
import com.buddysearch.presentation.manager.AuthManager;
import com.buddysearch.presentation.mvp.presenter.LoginPresenter;
import com.buddysearch.presentation.mvp.view.LoginView;
import com.buddysearch.presentation.mvp.view.impl.LoginViewImpl;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> {

    private static final int RC_SIGN_IN = 9001;

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            view.hideProgress();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                presenter.getAuthManager().signInGoogle(account, new AuthManager.SignInCallback() {
                    @Override
                    public void onSignInSuccess() {
                        view.navigateToUsers();
                    }

                    @Override
                    public void onSignInError() {
                        view.showMessage("Authentication failed.");
                    }
                });
            }
        }
    }

    @Override
    protected LoginView initView() {
        return new LoginViewImpl(this) {
            @Override
            public void navigateToUsers() {
                startActivity(new Intent(LoginActivity.this, UsersActivity.class));
                finish();
            }
        };
    }

    @Override
    protected LoginPresenter initPresenter() {
        return loginPresenter;
    }

    public void onClickSignInGoogle(View v) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        view.showProgress();
    }
}
