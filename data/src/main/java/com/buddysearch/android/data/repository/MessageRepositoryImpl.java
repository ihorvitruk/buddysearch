package com.buddysearch.android.data.repository;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.data.mapper.MessageEntityDtoMapper;
import com.buddysearch.android.data.store.MessageEntityStore;
import com.buddysearch.android.data.store.cache.MessageCache;
import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.repository.MessageRepository;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.repository.RepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MessageRepositoryImpl extends RepositoryImpl<MessageEntityStore, MessageCache, MessageEntityDtoMapper> implements MessageRepository {

    @Inject
    public MessageRepositoryImpl(NetworkManager networkManager,
                                 MessageEntityStore cloudStore,
                                 MessageCache cache,
                                 MessageEntityDtoMapper messageEntityDtoMapper) {
        super(networkManager, cloudStore, cache, messageEntityDtoMapper);
    }

    @Override
    public Observable<List<MessageDto>> getMessages(String peerId, Messenger messenger) {
        Observable<List<MessageEntity>> observable;
        if (networkManager.isNetworkAvailable()) {
            observable = cloudStore.getMessages(peerId).doOnNext(messageEntities -> cache.saveMessages(messageEntities));
        } else {
            observable = cache.getMessages(peerId).doOnNext(messagesEntities -> messenger.showFromCacheMessage());
        }
        return observable.map(messageEntities -> entityDtoMapper.map2(messageEntities));
    }

    @Override
    public Observable<Void> postMessage(MessageDto message, Messenger messenger) {
        if (networkManager.isNetworkAvailable()) {
            return cloudStore.postMessage(entityDtoMapper.map1(message));
        } else {
            return Observable.<Void>empty().doOnComplete(messenger::showNoNetworkMessage);
        }
    }

    @Override
    public Observable<Void> editMessage(MessageDto editedMessage, Messenger messenger) {
        if (networkManager.isNetworkAvailable()) {
            return cloudStore.editMessage(entityDtoMapper.map1(editedMessage));
        } else {
            return Observable.<Void>empty().doOnComplete(messenger::showNoNetworkMessage);
        }
    }

    @Override
    public Observable<Void> deleteMessage(MessageDto message, Messenger messenger) {
        if (networkManager.isNetworkAvailable()) {
            return cloudStore.deleteMessage(entityDtoMapper.map1(message));
        } else {
            return Observable.<Void>empty().doOnComplete(messenger::showNoNetworkMessage);
        }
    }
}
