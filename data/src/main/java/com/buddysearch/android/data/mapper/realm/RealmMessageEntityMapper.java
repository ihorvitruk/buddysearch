package com.buddysearch.android.data.mapper.realm;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.data.entity.realm.RealmMessageEntity;
import com.buddysearch.android.library.data.mapper.BaseMapper;

import javax.inject.Inject;

public class RealmMessageEntityMapper extends BaseMapper<RealmMessageEntity, MessageEntity> {

    @Inject
    public RealmMessageEntityMapper() {
    }

    @Override
    public RealmMessageEntity map1(MessageEntity o2) {
        if (o2 == null) {
            return null;
        }
        RealmMessageEntity realmMessageEntity = new RealmMessageEntity();
        realmMessageEntity.setMessageId(o2.getId());
        realmMessageEntity.setSenderId(o2.getSenderId());
        realmMessageEntity.setReceiverId(o2.getReceiverId());
        realmMessageEntity.setText(o2.getText());
        realmMessageEntity.setTimestamp(o2.getTimestamp());
        return realmMessageEntity;
    }

    @Override
    public MessageEntity map2(RealmMessageEntity o1) {
        if (o1 == null) {
            return null;
        }
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(o1.getMessageId());
        messageEntity.setSenderId(o1.getSenderId());
        messageEntity.setReceiverId(o1.getReceiverId());
        messageEntity.setText(o1.getText());
        messageEntity.setTimestamp(o1.getTimestamp());
        return messageEntity;
    }
}
