package com.buddysearch.android.presentation.ui.viewholder;

import android.view.Gravity;
import android.widget.LinearLayout;

import com.buddysearch.android.library.presentation.ui.viewholder.BaseViewHolder;
import com.buddysearch.android.presentation.databinding.ItemMessageBinding;
import com.buddysearch.android.presentation.mvp.model.MessageModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MessageViewHolder extends BaseViewHolder<ItemMessageBinding, MessageModel> {

    private String currentUserId;

    public MessageViewHolder(String currentUserId, ItemMessageBinding binding) {
        super(binding);
        this.currentUserId = currentUserId;
    }

    @Override
    public void bind(MessageModel messageModel) {
        LinearLayout linearLayout = (LinearLayout) binding.getRoot();
        if (messageModel.getSenderId().equals(currentUserId)) {
            linearLayout.setGravity(Gravity.RIGHT);
        } else {
            linearLayout.setGravity(Gravity.LEFT);
        }
        binding.tvText.setText(messageModel.getText());
        binding.tvTime.setText(formatTime(messageModel.getTimestamp()));
    }

    private String formatTime(long timestamp) {
        DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
        return df.format(timestamp);
    }
}
