package com.buddysearch.presentation.manager.impl;

import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.manager.AuthManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthManagerImpl implements AuthManager {

    private FirebaseAuth auth;

    private GoogleApiClient googleApiClient;

    public AuthManagerImpl(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
        this.auth = FirebaseAuth.getInstance();
    }

    @Override
    public void signInGoogle(GoogleSignInAccount acct, SignInCallback signInCallback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signInCallback.onSignInSuccess();
                    } else {
                        signInCallback.onSignInError();
                    }
                });
    }

    @Override
    public void signOut(SignOutCallback signOutCallback) {
        auth.signOut();
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
                status -> {
                    if (status.isSuccess()) {
                        signOutCallback.onSignOutSuccess();
                    } else {
                        signOutCallback.onSignOutError();
                    }
                });
    }

    @Override
    public boolean isSignedIn() {
        return getCurrentUser() != null;
    }

    @Override
    public User getCurrentUser() {
        User user = null;
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null) {
            user = new User();
            //TODO map FirebaseUser -> User
        }
        return user;
    }
}
