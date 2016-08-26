package com.buddysearch.android.presentation.ui.viewholder;

import com.buddysearch.android.library.presentation.ui.viewholder.BaseViewHolder;
import com.buddysearch.android.presentation.databinding.ItemUserBinding;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.view.UsersView;

public class UserViewHolder extends BaseViewHolder<ItemUserBinding, UserModel> {

    private UsersView usersView;

    public UserViewHolder(UsersView usersView, ItemUserBinding binding) {
        super(binding);
        this.usersView = usersView;
    }

    @Override
    public void bind(UserModel userModel, int position) {
        binding.getRoot().setOnClickListener(view -> usersView.navigateToDialog(userModel.getId()));
        binding.tvFirstName.setText(userModel.getFirstName());
        binding.tvLastName.setText(userModel.getLastName());
    }
}
