package com.buddysearch.android.data.store.cache.realm;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.data.entity.realm.RealmMessageEntity;
import com.buddysearch.android.data.mapper.realm.RealmMessageEntityMapper;
import com.buddysearch.android.data.store.cache.MessageCache;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

public class RealmMessageCache implements MessageCache {

    private Realm realm;

    private RealmMessageEntityMapper realmMessageEntityMapper;

    public RealmMessageCache(RealmMessageEntityMapper realmMessageEntityMapper) {
        realm = Realm.getDefaultInstance();
        this.realmMessageEntityMapper = realmMessageEntityMapper;
    }

    @Override
    public Observable<List<MessageEntity>> getMessages(String peerId) {
        List<MessageEntity> messageEntities = realmMessageEntityMapper
                .map2(realm.where(RealmMessageEntity.class).findAll());
        return Observable.just(messageEntities);
    }

    @Override
    public Observable<Void> postMessage(MessageEntity message) {
        //cache does not support this method
        return null;
    }

    @Override
    public Observable<Void> editMessage(MessageEntity editedMessage) {
        //cache does not support this method
        return null;
    }

    @Override
    public Observable<Void> deleteMessage(MessageEntity message) {
        //cache does not support this method
        return null;
    }

    @Override
    public void saveMessages(List<MessageEntity> messageEntities) {
        realm.beginTransaction();
        realm.delete(RealmMessageEntity.class);
        realm.copyToRealm(realmMessageEntityMapper.map1(messageEntities));
        realm.commitTransaction();
    }
}
