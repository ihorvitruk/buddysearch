package com.buddysearch.android.data.store.firebase;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.store.UserEntityStore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class FirebaseUserEntityStore extends FirebaseEntityStore implements UserEntityStore {

    public static final String CHILD_USERS = "users";

    @Inject
    public FirebaseUserEntityStore() {
    }

    public Observable<String> createUserIfNotExists(UserEntity userEntity) {
        DatabaseReference reference = database.child(CHILD_USERS).child(userEntity.getId());
        return createIfNotExists(reference, userEntity, userEntity.getId());
    }

    @Override
    public Observable<String> editUser(UserEntity userEntity) {
        DatabaseReference reference = database.child(CHILD_USERS).child(userEntity.getId());
        return update(reference, userEntity, userEntity.getId());
    }

    @Override
    public Observable<List<UserEntity>> getUsers() {
        Query query = database.child(CHILD_USERS).orderByKey();
        return getList(query, UserEntity.class, true);
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        Query query = database.child(CHILD_USERS).child(userId);
        return get(query, UserEntity.class, true);
    }
}
