package com.buddysearch.android.presentation.mapper;

import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.library.data.mapper.BaseMapper;
import com.buddysearch.android.presentation.mvp.model.MessageModel;

import javax.inject.Inject;

public class MessageDtoModelMapper extends BaseMapper<MessageDto, MessageModel> {

    @Inject
    public MessageDtoModelMapper() {
    }

    @Override
    public MessageDto map1(MessageModel o2) {
        if (o2 == null) {
            return null;
        }
        MessageDto messageDto = new MessageDto();
        messageDto.setMessageId(o2.getMessageId());
        messageDto.setSenderId(o2.getSenderId());
        messageDto.setReceiverId(o2.getReceiverId());
        messageDto.setText(o2.getText());
        messageDto.setTimestamp(o2.getTimestamp());
        return messageDto;
    }

    @Override
    public MessageModel map2(MessageDto o1) {
        if (o1 == null) {
            return null;
        }
        MessageModel messageModel = new MessageModel();
        messageModel.setMessageId(o1.getMessageId());
        messageModel.setSenderId(o1.getSenderId());
        messageModel.setReceiverId(o1.getReceiverId());
        messageModel.setText(o1.getText());
        messageModel.setTimestamp(o1.getTimestamp());
        return messageModel;
    }
}
