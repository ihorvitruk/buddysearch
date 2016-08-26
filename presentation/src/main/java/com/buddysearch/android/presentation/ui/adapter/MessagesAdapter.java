package com.buddysearch.android.presentation.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ItemMessageBinding;
import com.buddysearch.android.presentation.mvp.model.MessageModel;
import com.buddysearch.android.presentation.mvp.view.DialogView;
import com.buddysearch.android.presentation.ui.viewholder.MessageViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<MessageModel> items = new ArrayList<>();

    private String currentUserId;

    private DialogView dialogView;

    public MessagesAdapter(DialogView dialogView, String currentUserId) {
        this.dialogView = dialogView;
        this.currentUserId = currentUserId;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMessageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_message, parent, false);
        return new MessageViewHolder(dialogView, currentUserId, binding);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<MessageModel> messages) {
        items.clear();
        items.addAll(messages);
        notifyDataSetChanged();
    }
}
