package com.buddysearch.android.data.store.cache.realm;

import android.content.Context;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.entity.realm.RealmUserEntity;
import com.buddysearch.android.data.mapper.realm.RealmUserEntityMapper;
import com.buddysearch.android.data.store.cache.UserCache;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;

public class RealmUserCache implements UserCache {

    private Realm realm;

    private RealmUserEntityMapper realmUserEntityMapper;

    public RealmUserCache(Context context) {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
        realmUserEntityMapper = new RealmUserEntityMapper();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Observable<List<UserEntity>> getUsers() {
        return (Observable) realm.where(UserEntity.class).findAll().asObservable();
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        RealmUserEntity userEntity = realm.where(UserEntity.class).equalTo("id", userId).findFirst();
        Observable.from(new UserEntity[]{realmUserEntityMapper.map(userEntity)})
        }
    }

    @Override
    public void saveUser(String userId, UserEntity userEntity) {
        realm.copyToRealmOrUpdate(userEntity);
    }

    @Override
    public void saveUsers(List<UserEntity> userEntityList) {
        realm.delete(UserEntity.class);
        realm.copyToRealm(userEntityList);
    }
}
