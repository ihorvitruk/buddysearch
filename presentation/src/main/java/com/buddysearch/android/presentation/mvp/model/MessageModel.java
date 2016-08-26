package com.buddysearch.android.presentation.mvp.model;

import lombok.Data;

@Data
public class MessageModel {

    private String messageId;

    private String senderId;

    private String receiverId;

    private String text;

    private long timestamp;
}
