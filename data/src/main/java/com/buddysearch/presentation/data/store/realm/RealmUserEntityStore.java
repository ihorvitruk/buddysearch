package com.buddysearch.presentation.data.store.realm;

import com.buddysearch.presentation.data.entity.UserEntity;
import com.buddysearch.presentation.data.store.UserEntityStore;

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
