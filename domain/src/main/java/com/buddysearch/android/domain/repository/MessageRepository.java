package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.dto.MessageDto;

import java.util.List;

import rx.Observable;

public interface MessageRepository extends Repository {

    Observable<Boolean> postMessage(MessageDto message);
    
    Observable<List<MessageDto>> getMessages(String peerUserId);

    Observable<Boolean> editMessage(String messageId, MessageDto newMessage);

    Observable<Boolean> deleteMessage(String messageId);
}
