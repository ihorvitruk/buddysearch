package com.buddysearch.android.data.manager.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.exception.AuthException;
import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.store.firebase.FirebaseUserEntityStore;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rx.Subscriber;

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
    public void signInGoogle(GoogleSignInAccount acct, Subscriber<String> subscriber) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUser(task.getResult().getUser(), subscriber);
                    } else {
                        subscriber.onError(new AuthException());
                    }
                });
    }


    @Override
    public void signOut(Subscriber<String> subscriber) {
        String id = getCurrentUserId();
        auth.signOut();
        googleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
                        status -> {
                            if (status.isSuccess()) {
                                subscriber.onNext(id);
                            } else {
                                subscriber.onError(new AuthException());
                            }
                            googleApiClient.disconnect();
                        });
            }

            @Override
            public void onConnectionSuspended(int i) {
            }
        });
        googleApiClient.connect();
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

    private void saveUser(FirebaseUser user, Subscriber<String> subscriber) {
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
                subscriber.onNext(userEntity.getId());
            } else {
                subscriber.onError(new AuthException());
            }
        });
    }
}
