package com.buddysearch.android.presentation.ui.viewholder;

import com.buddysearch.android.library.presentation.ui.viewholder.BaseViewHolder;
import com.buddysearch.android.presentation.databinding.ItemMessageBinding;
import com.buddysearch.android.presentation.mvp.model.MessageModel;

import java.util.Date;

public class MessageViewHolder extends BaseViewHolder<ItemMessageBinding, MessageModel> {

    public MessageViewHolder(ItemMessageBinding binding) {
        super(binding);
    }

    @Override
    public void bind(MessageModel messageModel) {
        binding.tvText.setText(messageModel.getText());
        binding.tvTime.setText(new Date(messageModel.getTimestamp()).toString());
    }
}
