package com.buddysearch.presentation.data.store.firebase;

import com.buddysearch.presentation.data.entity.UserEntity;
import com.buddysearch.presentation.data.store.UserEntityStore;
import com.google.firebase.database.Query;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

public class FirebaseUserEntityStore extends FirebaseEntityStore implements UserEntityStore {

    public static final String CHILD_USERS = "users";

    @Inject
    public FirebaseUserEntityStore() {
    }

    @Override
    public Observable<List<UserEntity>> getUsers() {
        Query query = database.child(CHILD_USERS).orderByKey();
        return getList(query, UserEntity.class);
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        Query query = database.child(CHILD_USERS).child(userId);
        return get(query);
    }
}
