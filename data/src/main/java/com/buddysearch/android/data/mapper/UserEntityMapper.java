package com.buddysearch.android.data.mapper;

import android.text.TextUtils;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.domain.dto.Gender;
import com.buddysearch.android.domain.dto.UserDto;

import javax.inject.Inject;

import static com.buddysearch.android.domain.dto.Gender.FEMALE;
import static com.buddysearch.android.domain.dto.Gender.MALE;

public class UserEntityMapper extends BaseEntityMapper<UserEntity, UserDto> {

    @Inject
    public UserEntityMapper() {
    }

    @Override
    public UserDto map(UserEntity userEntity) {
        UserDto user = null;
        if (userEntity != null) {
            user = new UserDto();
            user.setId(userEntity.getId());
            user.setAge(userEntity.getAge());
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setGender(parse(userEntity.getGender()));
            user.setLatitude(userEntity.getLatitude());
            user.setLongitude(userEntity.getLongitude());
        }
        return user;
    }

    private Gender parse(String gender) {
        if (TextUtils.isEmpty(gender)) {
            return MALE;
        }
        if (gender.toLowerCase().contains(UserEntity.GENDER_FEMALE)) {
            return FEMALE;
        }
        return MALE;
    }
}
