package com.buddysearch.presentation.mvp.view;

import com.buddysearch.presentation.mvp.model.UserModel;

import java.util.List;

public interface UsersView extends View {
    void renderUsers(List<UserModel> users);
}
