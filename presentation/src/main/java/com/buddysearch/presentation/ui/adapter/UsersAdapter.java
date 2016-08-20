package com.buddysearch.presentation.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.buddysearch.presentation.R;
import com.buddysearch.presentation.databinding.ItemUserBinding;
import com.buddysearch.presentation.mvp.model.UserModel;
import com.buddysearch.presentation.ui.viewholder.UserViewHolder;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserModel> items = new ArrayList<>();

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_user, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<UserModel> users) {
        items.clear();
        items.addAll(users);
        notifyDataSetChanged();
    }
}
