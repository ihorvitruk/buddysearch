package com.buddysearch.android.data.store.cache.realm;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.entity.realm.RealmUserEntity;
import com.buddysearch.android.data.mapper.realm.RealmUserEntityMapper;
import com.buddysearch.android.data.store.cache.UserCache;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

public class RealmUserCache implements UserCache {

    private Realm realm;

    private RealmUserEntityMapper realmUserEntityMapper;

    public RealmUserCache(RealmUserEntityMapper realmUserEntityMapper) {
        realm = Realm.getDefaultInstance();
        this.realmUserEntityMapper = realmUserEntityMapper;
    }

    @Override
    public Observable<String> createUserIfNotExists(UserEntity userEntity) {
        //cache does not support this method
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Observable<List<UserEntity>> getUsers() {
        List<UserEntity> userEntities = realmUserEntityMapper
                .map2(realm.where(RealmUserEntity.class).findAll());
        return Observable.just(userEntities);
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        RealmUserEntity userEntity = realm.where(RealmUserEntity.class).equalTo("id", userId).findFirst();
        return Observable.just(realmUserEntityMapper.map2(userEntity));
    }

    @Override
    public void saveUser(String userId, UserEntity userEntity) {
        realm.executeTransaction(realm1 -> {
            realm.copyToRealmOrUpdate(realmUserEntityMapper.map1(userEntity));
        });
    }

    @Override
    public void saveUsers(List<UserEntity> userEntityList) {
        realm.executeTransaction(realm1 -> {
            realm1.delete(RealmUserEntity.class);
            realm1.copyToRealm(realmUserEntityMapper.map1(userEntityList));
        });
    }
}
