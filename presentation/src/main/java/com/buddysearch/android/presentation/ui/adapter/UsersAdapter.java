package com.buddysearch.android.presentation.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ItemUserBinding;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.view.UsersView;
import com.buddysearch.android.presentation.ui.viewholder.UserViewHolder;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserModel> items = new ArrayList<>();

    private UsersView usersView;

    public UsersAdapter(UsersView usersView) {
        this.usersView = usersView;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_user, parent, false);
        return new UserViewHolder(usersView, binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(items.get(position), position);
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
