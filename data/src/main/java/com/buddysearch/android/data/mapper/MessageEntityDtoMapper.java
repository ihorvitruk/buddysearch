package com.buddysearch.android.data.mapper;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.library.data.mapper.BaseMapper;

import javax.inject.Inject;

public class MessageEntityDtoMapper extends BaseMapper<MessageEntity, MessageDto> {

    @Inject
    public MessageEntityDtoMapper() {
    }

    @Override
    public MessageEntity map1(MessageDto o2) {
        if (o2 == null) {
            return null;
        }
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageId(o2.getMessageId());
        messageEntity.setSenderId(o2.getSenderId());
        messageEntity.setReceiverId(o2.getReceiverId());
        messageEntity.setText(o2.getText());
        messageEntity.setTimestamp(o2.getTimestamp());
        return messageEntity;
    }

    @Override
    public MessageDto map2(MessageEntity o1) {
        if (o1 == null) {
            return null;
        }
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageId(o1.getMessageId());
        messageDto.setSenderId(o1.getSenderId());
        messageDto.setReceiverId(o1.getReceiverId());
        messageDto.setText(o1.getText());
        messageDto.setTimestamp(o1.getTimestamp());
        return messageDto;
    }
}
