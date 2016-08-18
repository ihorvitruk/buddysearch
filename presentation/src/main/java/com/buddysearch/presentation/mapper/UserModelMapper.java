package com.buddysearch.presentation.mapper;

import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.model.UserModel;

import javax.inject.Inject;

public class UserModelMapper extends BaseModelMapper<User, UserModel> {

    @Inject
    public UserModelMapper() {
    }

    @Override
    public UserModel map(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setGender(user.getGender());
        userModel.setAge(user.getAge());
        userModel.setLatitude(user.getLatitude());
        userModel.setLongitude(user.getLongitude());
        return null;
    }
}
