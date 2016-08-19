package com.buddysearch.presentation.manager.impl;

import android.text.TextUtils;

import com.buddysearch.presentation.data.entity.UserEntity;
import com.buddysearch.presentation.data.store.firebase.FirebaseUserEntityStore;
import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.manager.AuthManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import junit.framework.Test;

public class AuthManagerImpl implements AuthManager {

    private FirebaseAuth auth;

    private DatabaseReference database;

    private GoogleApiClient googleApiClient;

    public AuthManagerImpl(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void signInGoogle(GoogleSignInAccount acct, SignInCallback signInCallback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUser(task.getResult().getUser(), signInCallback);
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
        return auth.getCurrentUser() != null;
    }

    @Override
    public String getCurrentUserId() {
        String id = null;
        if (isSignedIn()) {
            id = auth.getCurrentUser().getUid();
        }
        return id;
    }

    private void saveUser(FirebaseUser user, SignInCallback signInCallback) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getUid());
        if (!TextUtils.isEmpty(user.getDisplayName())) {
            String[] name = user.getDisplayName().split(" ");
            userEntity.setFirstName(name[0]);
            userEntity.setLastName(name[1]);
        }
        database.child(FirebaseUserEntityStore.CHILD_USERS)
                .child(user.getUid()).setValue(userEntity, (databaseError, databaseReference) -> {
            if (databaseError == null) {
                signInCallback.onSignInSuccess();
            } else {
                signInCallback.onSignInError();
            }
        });
    }
}
