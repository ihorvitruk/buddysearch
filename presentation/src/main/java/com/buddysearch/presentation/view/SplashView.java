package com.buddysearch.presentation.view;

import com.buddysearch.presentation.model.UserModel;

import java.util.List;

public interface SplashView extends View {
    void renderUsers(List<UserModel> users);
}
