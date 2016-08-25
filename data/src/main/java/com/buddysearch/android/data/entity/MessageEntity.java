package com.buddysearch.android.data.entity;

import lombok.Data;

@Data
public class MessageEntity {

    private String messageId;

    private String senderId;

    private String receiverId;

    private String text;

    private long timestamp;
}
