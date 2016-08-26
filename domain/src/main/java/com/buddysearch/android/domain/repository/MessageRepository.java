package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.dto.MessageDto;

import java.util.List;

import rx.Observable;

public interface MessageRepository extends Repository {

    Observable<List<MessageDto>> getMessages(String peerId);

    Observable<Void> postMessage(MessageDto message);

    Observable<Void> editMessage(MessageDto editedMessage);

    Observable<Void> deleteMessage(MessageDto message);
}
