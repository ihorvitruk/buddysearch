package com.buddysearch.android.data.mapper;

import android.text.TextUtils;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.domain.dto.Gender;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.library.data.mapper.BaseMapper;

import javax.inject.Inject;

import static com.buddysearch.android.domain.dto.Gender.FEMALE;
import static com.buddysearch.android.domain.dto.Gender.MALE;

public class UserEntityDtoMapper extends BaseMapper<UserEntity, UserDto> {

    @Inject
    public UserEntityDtoMapper() {
    }

    @Override
    public UserEntity map1(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setAge(userDto.getAge());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        Gender gender = userDto.getGender();
        if (gender != null) {
            userEntity.setGender(gender.name().toLowerCase());
        }
        userEntity.setLatitude(userDto.getLatitude());
        userEntity.setLongitude(userDto.getLongitude());
        return userEntity;
    }

    @Override
    public UserDto map2(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setAge(userEntity.getAge());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setGender(parse(userEntity.getGender()));
        userDto.setLatitude(userEntity.getLatitude());
        userDto.setLongitude(userEntity.getLongitude());
        return userDto;
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
