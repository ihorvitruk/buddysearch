package com.buddysearch.android.data.store.cache.realm;

import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

public class RealmUserCache implements UserCache {

    @Override
    public Observable<List<UserEntity>> getUsers() {
        return null;
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        return null;
    }

    @Override
    public void saveUser(String userId, UserEntity userEntity) {

    }

    @Override
    public void saveUsers(List<UserEntity> userEntityList) {

    }
}
