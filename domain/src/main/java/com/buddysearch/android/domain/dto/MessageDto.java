package com.buddysearch.android.domain.dto;

import lombok.Data;

@Data
public class MessageDto {

    private String messageId;

    private String senderId;

    private String receiverId;

    private String text;

    private long timestamp;
}
