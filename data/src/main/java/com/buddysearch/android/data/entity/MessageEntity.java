package com.buddysearch.android.data.entity;

import com.buddysearch.android.library.data.entity.Entity;

import lombok.Data;

@Data
public class MessageEntity implements Entity {

    private String id;

    private String senderId;

    private String receiverId;

    private String text;

    private long timestamp;
}
