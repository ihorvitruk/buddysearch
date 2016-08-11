package com.buddysearch.android.data.store.firebase;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.store.UserEntityStore;
import com.google.firebase.database.Query;

import java.util.List;

import rx.Observable;

public class FirebaseUserEntityStore extends FirebaseEntityStore implements UserEntityStore {

    private final String CHILD_USERS = "users";

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
