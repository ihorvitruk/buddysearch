package com.buddysearch.android.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivityDialogBinding;
import com.buddysearch.android.presentation.mvp.model.MessageModel;
import com.buddysearch.android.presentation.mvp.presenter.DialogPresenter;
import com.buddysearch.android.presentation.mvp.view.DialogView;
import com.buddysearch.android.presentation.mvp.view.impl.DialogViewImpl;
import com.buddysearch.android.presentation.ui.adapter.MessagesAdapter;

import java.util.List;

import javax.inject.Inject;

public class DialogActivity extends BaseDaggerActivity<DialogView, DialogPresenter, ActivityDialogBinding> {

    public static final String KEY_PEER_ID = "peer_id";

    @Inject
    DialogPresenter dialogPresenter;

    private MessagesAdapter messagesAdapter;

    public static void start(Context context, String peerId) {
        Intent intent = new Intent(context, DialogActivity.class);
        intent.putExtra(KEY_PEER_ID, peerId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPeerId();
        initMessagesRecyclerView();
        initSendMessageButton();
    }

    @Override
    protected DialogView initView() {
        return new DialogViewImpl(this) {
            @Override
            public void renderMessages(List<MessageModel> messages) {
                messagesAdapter.setItems(messages);
            }

            @Override
            public void setTitle(String title) {
                DialogActivity.this.setTitle(title);
            }
        };
    }

    @Override
    protected DialogPresenter initPresenter() {
        getActivityComponent().inject(this);
        return dialogPresenter;
    }

    @Override
    protected ActivityDialogBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_dialog);
    }

    private void initMessagesRecyclerView() {
        messagesAdapter = new MessagesAdapter();
        binding.rvUsers.setAdapter(messagesAdapter);
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initPeerId() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String peerId = extras.getString(KEY_PEER_ID);
            presenter.setPeerId(peerId);
        }
    }

    private void initSendMessageButton() {
        binding.btnSend.setOnClickListener(view1 -> presenter.sendMessage(binding.tvInputMessage.getText().toString()));
    }
}
