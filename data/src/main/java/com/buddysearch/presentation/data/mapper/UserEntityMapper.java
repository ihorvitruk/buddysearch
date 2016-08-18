package com.buddysearch.presentation.data.mapper;

import android.text.TextUtils;

import com.buddysearch.presentation.data.entity.UserEntity;
import com.buddysearch.presentation.domain.dto.Gender;
import com.buddysearch.presentation.domain.dto.User;

import javax.inject.Inject;

import static com.buddysearch.presentation.domain.dto.Gender.FEMALE;
import static com.buddysearch.presentation.domain.dto.Gender.MALE;

public class UserEntityMapper extends BaseEntityMapper<UserEntity, User> {

    @Inject
    public UserEntityMapper() {
    }

    @Override
    public User map(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User();
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
