package com.buddysearch.presentation.ui.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public abstract class BaseViewHolder<BINDING extends ViewDataBinding, MODEL> extends RecyclerView.ViewHolder {

    protected BINDING binding;

    public BaseViewHolder(BINDING binding) {
        super(binding.getRoot());
    }

    public abstract void bind(MODEL model);
}
