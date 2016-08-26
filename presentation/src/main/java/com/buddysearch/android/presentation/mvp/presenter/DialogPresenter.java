package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.message.GetMessages;
import com.buddysearch.android.domain.interactor.message.PostMessage;
import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultSubscriber;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.mapper.MessageDtoModelMapper;
import com.buddysearch.android.presentation.mvp.model.MessageModel;
import com.buddysearch.android.presentation.mvp.view.DialogView;

import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

public class DialogPresenter extends BasePresenter<DialogView> {

    private GetMessages getMessages;

    private PostMessage postMessage;

    private GetUser getUser;

    @Setter
    private String peerId;

    private MessageDtoModelMapper messageDtoModelMapper;

    private AuthManager authManager;

    @Inject
    public DialogPresenter(NetworkManager networkManager,
                           AuthManager authManager,
                           GetMessages getMessages,
                           PostMessage postMessage,
                           GetUser getUser,
                           MessageDtoModelMapper messageDtoModelMapper) {
        super(networkManager);
        this.authManager = authManager;
        this.getMessages = getMessages;
        this.postMessage = postMessage;
        this.getUser = getUser;
        this.messageDtoModelMapper = messageDtoModelMapper;
    }

    public void sendMessage(String message) {
        view.showProgress();
        MessageModel messageModel = new MessageModel();
        messageModel.setSenderId(authManager.getCurrentUserId());
        messageModel.setReceiverId(peerId);
        messageModel.setText(message);
        messageModel.setTimestamp(System.currentTimeMillis());
        postMessage.execute(messageDtoModelMapper.map1(messageModel), new DefaultSubscriber<Void>(view) {
            @Override
            public void onNext(Void aVoid) {
                super.onNext(aVoid);
                view.clearInput();
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(e.getMessage());
                view.hideProgress();
            }
        });
    }

    @Override
    public void refreshData() {
        getMessages();
        getPeerUser();
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        refreshData();
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
        getMessages.unsubscribe();
        postMessage.unsubscribe();
        getUser.unsubscribe();
    }

    private void getMessages() {
        view.showProgress();
        getMessages.execute(peerId, new DefaultSubscriber<List<MessageDto>>(view) {
            @Override
            public void onNext(List<MessageDto> messages) {
                super.onNext(messages);
                view.renderMessages(messageDtoModelMapper.map2(messages));
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(e.getMessage());
                view.hideProgress();
            }
        });
    }

    private void getPeerUser() {
        getUser.execute(peerId, new DefaultSubscriber<UserDto>(view) {
            @Override
            public void onNext(UserDto userDto) {
                super.onNext(userDto);
                view.setTitle(userDto.getFirstName() + " " + userDto.getLastName());
            }
        });
    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}
