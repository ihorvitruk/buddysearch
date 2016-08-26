package com.buddysearch.android.presentation.ui.viewholder;

import android.view.Gravity;
import android.widget.LinearLayout;

import com.buddysearch.android.library.presentation.ui.viewholder.BaseViewHolder;
import com.buddysearch.android.presentation.databinding.ItemMessageBinding;
import com.buddysearch.android.presentation.mvp.model.MessageModel;
import com.buddysearch.android.presentation.mvp.view.DialogView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MessageViewHolder extends BaseViewHolder<ItemMessageBinding, MessageModel> {

    private String currentUserId;

    private DialogView dialogView;

    public MessageViewHolder(DialogView dialogView,
                             String currentUserId,
                             ItemMessageBinding binding) {
        super(binding);
        this.dialogView = dialogView;
        this.currentUserId = currentUserId;
    }

    @Override
    public void bind(MessageModel messageModel, int position) {
        LinearLayout root = (LinearLayout) binding.getRoot();
        if (messageModel.getSenderId().equals(currentUserId)) {
            root.setGravity(Gravity.RIGHT);
        } else {
            root.setGravity(Gravity.LEFT);
        }
        root.setOnClickListener(view ->
                dialogView.showMessageMenu(messageModel, position));
        binding.tvText.setText(messageModel.getText());
        binding.tvTime.setText(formatTime(messageModel.getTimestamp()));
    }

    private String formatTime(long timestamp) {
        DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
        return df.format(timestamp);
    }
}
