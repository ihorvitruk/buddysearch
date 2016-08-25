package com.buddysearch.android.data.store;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.library.data.store.EntityStore;

import java.util.List;

import rx.Observable;

public interface MessageEntityStore extends EntityStore {

    Observable<Boolean> postMessage(MessageEntity message);

    Observable<List<MessageEntity>> getMessages(String peerUserId);

    Observable<Boolean> editMessage(String messageId, MessageEntity newMessage);

    Observable<Boolean> deleteMessage(String messageId);
}
