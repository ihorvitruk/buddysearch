package com.buddysearch.android.data.manager;

import com.buddysearch.android.domain.interactor.user.CreateUser;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import rx.Subscriber;

public interface AuthManager {

    void signInGoogle(GoogleSignInAccount acct, Subscriber<String> signInSubscriber, CreateUser createUser);

    void signOut(Subscriber<String> signOutSubscriber);

    boolean isSignedIn();

    String getCurrentUserId();
}
