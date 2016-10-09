package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.MessageDto;

import java.util.List;

import rx.Observable;

public interface MessageRepository extends Repository {

    Observable<List<MessageDto>> getMessages(String peerId, Messenger messenger);

    Observable<Void> postMessage(MessageDto message, Messenger messenger);

    Observable<Void> editMessage(MessageDto editedMessage, Messenger messenger);

    Observable<Void> deleteMessage(MessageDto message, Messenger messenger);
}
