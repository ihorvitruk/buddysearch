package com.buddysearch.android.data.manager.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.buddysearch.android.data.manager.AuthException;
import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.user.CreateUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import io.reactivex.observers.DisposableObserver;
import io.realm.Realm;

public class AuthManagerImpl implements AuthManager {

    private FirebaseAuth auth;

    private GoogleApiClient googleApiClient;

    public AuthManagerImpl(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
        this.auth = FirebaseAuth.getInstance();
    }

    @Override
    public void signInGoogle(GoogleSignInAccount acct, DisposableObserver<String> observer, CreateUser createUser) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (!observer.isDisposed()) {
                        if (task.isSuccessful()) {
                            saveUser(task.getResult().getUser(), observer, createUser);
                        } else {
                            observer.onError(new FirebaseException(task.getException().getMessage()));
                        }
                    }
                });
    }


    @Override
    public void signOut(DisposableObserver<String> observer) {
        String id = getCurrentUserId();
        auth.signOut();
        googleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
                        status -> {
                            googleApiClient.disconnect();
                            googleApiClient.unregisterConnectionCallbacks(this);
                            if (!observer.isDisposed()) {
                                if (status.isSuccess()) {
                                    deleteCache();
                                    observer.onNext(id);
                                } else {
                                    observer.onError(new AuthException());
                                }
                            }
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

    private void saveUser(FirebaseUser user, DisposableObserver<String> observer, CreateUser createUser) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUid());
        if (!TextUtils.isEmpty(user.getDisplayName())) {
            String[] name = user.getDisplayName().split(" ");
            userDto.setFirstName(name[0]);
            userDto.setLastName(name[1]);
        }

        createUser.execute(userDto, observer);
    }

    private void deleteCache() {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.deleteAll());
    }
}
