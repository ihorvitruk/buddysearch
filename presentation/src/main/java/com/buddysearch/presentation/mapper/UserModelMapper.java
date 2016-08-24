package com.buddysearch.presentation.mapper;

import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.library.data.mapper.BaseMapper;
import com.buddysearch.presentation.mvp.model.UserModel;

import javax.inject.Inject;

public class UserModelMapper extends BaseMapper<UserDto, UserModel> {

    @Inject
    public UserModelMapper() {
    }

    @Override
    public UserModel map(UserDto user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setGender(user.getGender());
        userModel.setAge(user.getAge());
        userModel.setLatitude(user.getLatitude());
        userModel.setLongitude(user.getLongitude());
        return userModel;
    }
}
