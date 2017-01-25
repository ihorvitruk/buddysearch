package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.util.StringUtil;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.message.DeleteMessage;
import com.buddysearch.android.domain.interactor.message.EditMessage;
import com.buddysearch.android.domain.interactor.message.GetMessages;
import com.buddysearch.android.domain.interactor.message.PostMessage;
import com.buddysearch.android.domain.interactor.user.GetUser;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultObserver;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.ViewScope;
import com.buddysearch.android.presentation.mapper.MessageDtoModelMapper;
import com.buddysearch.android.presentation.mvp.model.MessageModel;
import com.buddysearch.android.presentation.mvp.view.DialogView;

import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

@ViewScope
public class DialogPresenter extends BasePresenter<DialogView> {

    @Getter
    private static String currentPeerId;

    private GetMessages getMessages;

    private PostMessage postMessage;

    private DeleteMessage deleteMessage;

    private EditMessage editMessage;

    private GetUser getUser;

    @Getter
    @Setter
    private String peerId;

    private MessageDtoModelMapper messageDtoModelMapper;

    private AuthManager authManager;

    @Inject
    public DialogPresenter(NetworkManager networkManager,
                           AuthManager authManager,
                           GetMessages getMessages,
                           PostMessage postMessage,
                           DeleteMessage deleteMessage,
                           EditMessage editMessage,
                           GetUser getUser,
                           MessageDtoModelMapper messageDtoModelMapper) {
        super(networkManager);
        this.authManager = authManager;
        this.getMessages = getMessages;
        this.postMessage = postMessage;
        this.deleteMessage = deleteMessage;
        this.editMessage = editMessage;
        this.getUser = getUser;
        this.messageDtoModelMapper = messageDtoModelMapper;
    }

    public void sendMessage(String message) {
        view.showProgress(R.string.sending);
        MessageModel messageModel = new MessageModel();
        messageModel.setSenderId(authManager.getCurrentUserId());
        messageModel.setReceiverId(peerId);
        messageModel.setText(message);
        messageModel.setTimestamp(System.currentTimeMillis());
        postMessage.execute(messageDtoModelMapper.map1(messageModel), new DefaultObserver<Void>(view) {
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

    public void deleteMessage(MessageModel messageModel) {
        view.showProgress(R.string.deleting);
        deleteMessage.execute(messageDtoModelMapper.map1(messageModel),
                new DefaultObserver<Void>(null) {
                    @Override
                    public void onNext(Void aVoid) {
                        super.onNext(aVoid);
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

    public void editMessage(MessageModel messageModel) {
        view.showProgress(R.string.editing);
        editMessage.execute(messageDtoModelMapper.map1(messageModel),
                new DefaultObserver<Void>(view) {
                    @Override
                    public void onNext(Void aVoid) {
                        super.onNext(aVoid);
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
    public void resume() {
        super.resume();
        currentPeerId = peerId;
    }

    @Override
    public void pause() {
        super.pause();
        currentPeerId = null;
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
        getMessages.dispose();
        postMessage.dispose();
        deleteMessage.dispose();
        editMessage.dispose();
        getUser.dispose();
    }

    private void getMessages() {
        view.showProgress();
        getMessages.execute(peerId, new DefaultObserver<List<MessageDto>>(view) {
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
        getUser.execute(peerId, new DefaultObserver<UserDto>(view) {
            @Override
            public void onNext(UserDto userDto) {
                super.onNext(userDto);
                view.setTitle(StringUtil.concatLinearly(" ", userDto.getFirstName(), userDto.getLastName()));
            }
        });
    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}
