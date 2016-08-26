package com.buddysearch.android.presentation.mvp.view;

import com.buddysearch.android.library.presentation.mvp.view.View;
import com.buddysearch.android.presentation.mvp.model.MessageModel;

import java.util.List;

public interface DialogView extends View {

    void renderMessages(List<MessageModel> messages);

    void setTitle(String title);

    void clearInput();

    void showMessageMenu(MessageModel message, int position);
}
