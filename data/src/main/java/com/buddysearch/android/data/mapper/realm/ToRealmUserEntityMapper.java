package com.buddysearch.android.data.mapper.realm;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.entity.realm.RealmUserEntity;
import com.buddysearch.android.library.data.mapper.BaseMapper;

import javax.inject.Inject;

public class ToRealmUserEntityMapper extends BaseMapper<UserEntity, RealmUserEntity> {

    @Inject
    public ToRealmUserEntityMapper() {
    }

    @Override
    public RealmUserEntity map(UserEntity from) {
        RealmUserEntity realmUserEntity = new RealmUserEntity();
        realmUserEntity.setId(from.getId());
        realmUserEntity.setFirstName(from.getFirstName());
        realmUserEntity.setLastName(from.getLastName());
        realmUserEntity.setAge(from.getAge());
        realmUserEntity.setGender(from.getGender());
        realmUserEntity.setLatitude(from.getLatitude());
        realmUserEntity.setLongitude(from.getLongitude());
        return realmUserEntity;
    }
}

