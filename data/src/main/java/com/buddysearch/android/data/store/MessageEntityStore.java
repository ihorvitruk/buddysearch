package com.buddysearch.android.data.store;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.library.data.store.EntityStore;

import java.util.List;

import rx.Observable;

public interface MessageEntityStore extends EntityStore {

    Observable<List<MessageEntity>> getMessages(String peerId);

    Observable<Void> postMessage(MessageEntity message);

    Observable<Void> editMessage(MessageEntity editedMessage);

    Observable<Void> deleteMessage(MessageEntity message);
}
