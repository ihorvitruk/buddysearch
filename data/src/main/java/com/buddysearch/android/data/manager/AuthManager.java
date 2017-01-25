package com.buddysearch.android.data.manager;

import com.buddysearch.android.domain.interactor.user.CreateUser;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import io.reactivex.observers.DisposableObserver;

public interface AuthManager {

    void signInGoogle(GoogleSignInAccount acct, DisposableObserver<String> signInObserver, CreateUser createUser);

    void signOut(DisposableObserver<String> signOutObserver);

    boolean isSignedIn();

    String getCurrentUserId();
}
