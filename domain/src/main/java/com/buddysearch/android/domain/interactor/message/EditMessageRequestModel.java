package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.dto.MessageDto;

import lombok.Value;

@Value
public class EditMessageRequestModel {
    private String messageId;
    private MessageDto messageDto;
}
