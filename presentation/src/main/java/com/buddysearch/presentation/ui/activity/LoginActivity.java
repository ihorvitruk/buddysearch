package com.buddysearch.presentation.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.buddysearch.presentation.R;
import com.buddysearch.presentation.databinding.ActivityLoginBinding;
import com.buddysearch.presentation.mvp.presenter.LoginPresenter;
import com.buddysearch.presentation.mvp.view.LoginView;
import com.buddysearch.presentation.mvp.view.impl.LoginViewImpl;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter, ActivityLoginBinding> {

    private static final int RC_SIGN_IN = 9001;

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleApiClient.connect();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        googleApiClient.disconnect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            view.hideProgress();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                view.showProgress();
                GoogleSignInAccount account = result.getSignInAccount();
                presenter.signInWithGoogle(account);
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
        getActivityComponent().inject(this);
        return loginPresenter;
    }

    @Override
    protected ActivityLoginBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    public void onClickSignInGoogle(View v) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        googleApiClientSignOut();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        view.showProgress();
    }

    private void googleApiClientSignOut() {
        try {
            Auth.GoogleSignInApi.signOut(googleApiClient);
        } catch (Exception e) {
        }
    }
}
