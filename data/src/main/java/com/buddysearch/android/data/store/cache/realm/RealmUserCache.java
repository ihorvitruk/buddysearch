package com.buddysearch.android.data.store.cache.realm;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.store.cache.UserCache;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class RealmUserCache implements UserCache {

    @Override
    public Observable<List<UserEntity>> getUsers() {
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Firstname");
        userEntity.setLastName("LastName");
        userEntities.add(userEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity.setFirstName("Firstname 2");
        userEntity.setLastName("LastName 2");
        userEntities.add(userEntity2);

        List<List<UserEntity>> list = new ArrayList<>();
        list.add(userEntities);
        return Observable.from(list);
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        List<UserEntity> userEntities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Firstname");
        userEntity.setLastName("LastName");
        userEntities.add(userEntity);
        return Observable.from(userEntities);
    }

    @Override
    public void saveUser(String userId, UserEntity userEntity) {

    }

    @Override
    public void saveUsers(List<UserEntity> userEntityList) {

    }
}
