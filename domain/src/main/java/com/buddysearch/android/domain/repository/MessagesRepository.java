package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.dto.MessageDto;

import java.util.List;

import rx.Observable;

public interface MessagesRepository extends Repository {

    Observable<Boolean> postMessage(MessageDto message);

    Observable<MessageDto> getMessage(String messageId);

    Observable<List<MessageDto>> getMessages(String peerUserId);

    Observable<Boolean> editMessage(String messageId, MessageDto newMessage);

    Observable<Boolean> deleteMessage(String messageId);
}
