package com.buddysearch.android.data.store.cache.realm;

import android.content.Context;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.entity.realm.RealmUserEntity;
import com.buddysearch.android.data.mapper.realm.FromRealmUserEntityMapper;
import com.buddysearch.android.data.mapper.realm.ToRealmUserEntityMapper;
import com.buddysearch.android.data.store.cache.UserCache;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;

public class RealmUserCache implements UserCache {

    private Realm realm;

    private FromRealmUserEntityMapper fromRealmUserEntityMapper;

    private ToRealmUserEntityMapper toRealmUserEntityMapper;

    public RealmUserCache(Context context,
                          FromRealmUserEntityMapper fromRealmUserEntityMapper,
                          ToRealmUserEntityMapper toRealmUserEntityMapper) {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
        this.fromRealmUserEntityMapper = fromRealmUserEntityMapper;
        this.toRealmUserEntityMapper = toRealmUserEntityMapper;

    }

    @SuppressWarnings("unchecked")
    @Override
    public Observable<List<UserEntity>> getUsers() {
        List<UserEntity> userEntities = fromRealmUserEntityMapper.map(
                realm.where(RealmUserEntity.class).findAll());
        return Observable.just(userEntities);
    }

    @Override
    public Observable<UserEntity> getUser(String userId) {
        RealmUserEntity userEntity = realm.where(RealmUserEntity.class).equalTo("id", userId).findFirst();
        return Observable.just(fromRealmUserEntityMapper.map(userEntity));
    }

    @Override
    public void saveUser(String userId, UserEntity userEntity) {
        realm.copyToRealmOrUpdate(toRealmUserEntityMapper.map(userEntity));
    }

    @Override
    public void saveUsers(List<UserEntity> userEntityList) {
        realm.delete(RealmUserEntity.class);
        realm.copyToRealm(toRealmUserEntityMapper.map(userEntityList));
    }
}
