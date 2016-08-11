package com.buddysearch.android.data.store.realm;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.store.UserEntityStore;

import java.util.List;

import rx.Observable;

public class RealmUserEntityStore implements UserEntityStore {
    @Override
    public Observable<List<UserEntity>> getUsers() {
        return null;
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        return null;
    }
}
