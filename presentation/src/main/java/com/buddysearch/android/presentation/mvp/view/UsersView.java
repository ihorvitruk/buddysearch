package com.buddysearch.android.presentation.mvp.view;

import com.buddysearch.android.library.presentation.mvp.view.View;
import com.buddysearch.android.presentation.mvp.model.UserModel;

import java.util.List;

public interface UsersView extends View {
    void renderCurrentUser(UserModel user);
    void renderUsers(List<UserModel> users);
    void navigateToSplash();
}
