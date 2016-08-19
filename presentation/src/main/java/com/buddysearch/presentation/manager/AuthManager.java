package com.buddysearch.presentation.manager;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface AuthManager {

    void signInGoogle(GoogleSignInAccount acct, SignInCallback signInCallback);

    void signOut(SignOutCallback signOutCallback);

    boolean isSignedIn();

    String getCurrentUserId();

    interface SignInCallback {

        void onSignInSuccess();

        void onSignInError();
    }

    interface SignOutCallback {

        void onSignOutSuccess();

        void onSignOutError();
    }
}
