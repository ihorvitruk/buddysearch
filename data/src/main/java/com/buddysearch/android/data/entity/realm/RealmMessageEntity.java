package com.buddysearch.android.data.entity.realm;

import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RealmMessageEntity {

    @PrimaryKey
    private String messageId;

    private String senderId;

    private String receiverId;

    private String text;

    private long timestamp;
}
