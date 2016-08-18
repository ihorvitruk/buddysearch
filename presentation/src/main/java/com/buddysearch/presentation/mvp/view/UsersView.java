package com.buddysearch.presentation.mvp.view;

import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.mvp.model.UserModel;

import java.util.List;

public interface UsersView extends View {
    void renderCurrentUser(User user);
    void renderUsers(List<UserModel> users);
}
