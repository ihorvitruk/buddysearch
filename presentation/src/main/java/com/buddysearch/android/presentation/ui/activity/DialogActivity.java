package com.buddysearch.android.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivityDialogBinding;
import com.buddysearch.android.presentation.di.component.ViewComponent;
import com.buddysearch.android.presentation.mvp.model.MessageModel;
import com.buddysearch.android.presentation.mvp.presenter.DialogPresenter;
import com.buddysearch.android.presentation.mvp.view.DialogView;
import com.buddysearch.android.presentation.mvp.view.impl.DialogViewImpl;
import com.buddysearch.android.presentation.ui.adapter.MessagesAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

public class DialogActivity extends BaseDaggerActivity<DialogView, DialogPresenter, ActivityDialogBinding> {

    public static final String KEY_PEER_ID = "peer_id";

    @Inject
    Lazy<DialogPresenter> dialogPresenter;

    private MessagesAdapter messagesAdapter;

    public static void start(Context context, String peerId) {
        Intent intent = new Intent(context, DialogActivity.class);
        intent.putExtra(KEY_PEER_ID, peerId);
        context.startActivity(intent);
    }

    @Override
    public void onLoadFinished() {
        super.onLoadFinished();
        initPeerId();
        initSendMessageButton();
        initMessagesRecyclerView();
    }

    @Override
    protected DialogView initView() {
        return new DialogViewImpl(this) {
            @Override
            public void renderMessages(List<MessageModel> messages) {
                messagesAdapter.setItems(messages);
                if (messagesAdapter.getItemCount() > 0) {
                    binding.rvUsers.scrollToPosition(messagesAdapter.getItemCount() - 1);
                }
            }

            @Override
            public void setTitle(String title) {
                DialogActivity.this.setTitle(title);
            }

            @Override
            public void clearInput() {
                binding.tvInputMessage.getText().clear();
            }

            @Override
            public void showMessageMenu(MessageModel message, int position) {
                showMessagePopup(message, position);
            }
        };
    }

    @Override
    protected Lazy<DialogPresenter> initPresenter() {
        return dialogPresenter;
    }

    @Override
    protected ActivityDialogBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_dialog);
    }

    @Override
    protected void injectViewComponent(ViewComponent viewComponent) {
        viewComponent.inject(this);
    }

    private void initMessagesRecyclerView() {
        messagesAdapter = new MessagesAdapter(view, presenter.getAuthManager().getCurrentUserId());
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
        binding.btnSend.setOnClickListener(view1 -> {
            String message = binding.tvInputMessage.getText().toString();
            if (!TextUtils.isEmpty(message)) {
                presenter.sendMessage(message);
            }
        });
    }

    private void showMessagePopup(MessageModel message, int position) {
        View item = binding.rvUsers.getChildAt(position).findViewById(R.id.tv_text);
        PopupMenu popupMenu = new PopupMenu(item.getContext(), item);
        boolean findItemVisibility = message.getSenderId().equals(presenter.getAuthManager().getCurrentUserId())
                && position == messagesAdapter.getItemCount() - 1;
        popupMenu.inflate(R.menu.menu_message_item);
        popupMenu.getMenu().findItem(R.id.item_edit).setVisible(findItemVisibility);
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_delete:
                    presenter.deleteMessage(message);
                    return true;
                case R.id.item_edit:
                    showEditMessageDialog(message);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

    private void showEditMessageDialog(MessageModel messageModel) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        editText.setText(messageModel.getText());
        dialog.setView(editText);
        dialog.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {
            messageModel.setText(editText.getText().toString());
            presenter.editMessage(messageModel);
        });
        dialog.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> {
        });
        dialog.show();
    }

}
