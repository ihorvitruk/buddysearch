package com.buddysearch.android.data.store.cache;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.data.store.MessageEntityStore;
import com.buddysearch.android.library.data.store.cache.Cache;

import java.util.List;

public interface MessageCache extends MessageEntityStore, Cache {

    void saveMessages(List<MessageEntity> messageEntities);
}
