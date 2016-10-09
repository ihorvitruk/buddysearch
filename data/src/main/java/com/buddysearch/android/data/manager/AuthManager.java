package com.buddysearch.android.data.manager;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import rx.Subscriber;

public interface AuthManager {

    void signInGoogle(GoogleSignInAccount acct, Subscriber<String> signInSubscriber);

    void signOut(Subscriber<String> signOutSubscriber);

    boolean isSignedIn();

    String getCurrentUserId();
}
