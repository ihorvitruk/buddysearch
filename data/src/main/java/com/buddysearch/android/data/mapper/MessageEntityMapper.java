package com.buddysearch.android.data.mapper;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.library.data.mapper.BaseMapper;

import javax.inject.Inject;

public class MessageEntityMapper extends BaseMapper<MessageEntity, MessageDto> {

    @Inject
    public MessageEntityMapper() {
    }

    @Override
    public MessageDto map(MessageEntity messageEntity) {
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageId(messageEntity.getMessageId());
        messageDto.setSenderId(messageEntity.getSenderId());
        messageDto.setReceiverId(messageEntity.getReceiverId());
        messageDto.setText(messageEntity.getText());
        messageDto.setTimestamp(messageEntity.getTimestamp());
        return messageDto;
    }
}
