package com.buddysearch.android.presentation.mapper;

import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.library.data.mapper.BaseMapper;
import com.buddysearch.android.presentation.mvp.model.UserModel;

import javax.inject.Inject;

public class UserDtoModelMapper extends BaseMapper<UserDto, UserModel> {

    @Inject
    public UserDtoModelMapper() {
    }

    @Override
    public UserDto map1(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userModel.getId());
        userDto.setFirstName(userModel.getFirstName());
        userDto.setLastName(userModel.getLastName());
        userDto.setGender(userModel.getGender());
        userDto.setAge(userModel.getAge());
        userDto.setLatitude(userModel.getLatitude());
        userDto.setLongitude(userModel.getLongitude());
        return userDto;
    }

    @Override
    public UserModel map2(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setId(userDto.getId());
        userModel.setFirstName(userDto.getFirstName());
        userModel.setLastName(userDto.getLastName());
        userModel.setGender(userDto.getGender());
        userModel.setAge(userDto.getAge());
        userModel.setLatitude(userDto.getLatitude());
        userModel.setLongitude(userDto.getLongitude());
        return userModel;
    }
}
