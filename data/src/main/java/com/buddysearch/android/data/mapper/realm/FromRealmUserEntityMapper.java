package com.buddysearch.android.data.mapper.realm;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.entity.realm.RealmUserEntity;
import com.buddysearch.android.library.data.mapper.BaseMapper;

import javax.inject.Inject;

public class FromRealmUserEntityMapper extends BaseMapper<RealmUserEntity, UserEntity> {

    @Inject
    public FromRealmUserEntityMapper() {
    }

    @Override
    public UserEntity map(RealmUserEntity from) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(from.getId());
        userEntity.setFirstName(from.getFirstName());
        userEntity.setLastName(from.getLastName());
        userEntity.setAge(from.getAge());
        userEntity.setGender(from.getGender());
        userEntity.setLatitude(from.getLatitude());
        userEntity.setLongitude(from.getLongitude());
        return userEntity;
    }
}
