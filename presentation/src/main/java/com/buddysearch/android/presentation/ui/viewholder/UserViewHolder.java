package com.buddysearch.android.presentation.ui.viewholder;

import com.buddysearch.android.library.presentation.ui.viewholder.BaseViewHolder;
import com.buddysearch.android.presentation.databinding.ItemUserBinding;
import com.buddysearch.android.presentation.mvp.model.UserModel;

public class UserViewHolder extends BaseViewHolder<ItemUserBinding, UserModel> {

    public UserViewHolder(ItemUserBinding binding) {
        super(binding);
    }

    @Override
    public void bind(UserModel userModel) {
        binding.tvFirstName.setText(userModel.getFirstName());
        binding.tvLastName.setText(userModel.getLastName());
    }
}
