package com.buddysearch.android.data.store.firebase;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.data.store.MessageEntityStore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class FirebaseMessageEntityStore extends FirebaseEntityStore implements MessageEntityStore {

    public static final String CHILD_MESSAGES = "messages";

    @Inject
    public FirebaseMessageEntityStore() {
    }

    @Override
    public Observable<List<MessageEntity>> getMessages(String peerId) {
        Query query = database.child(CHILD_MESSAGES).child(peerId);
        return getList(query, MessageEntity.class);
    }

    @Override
    public Observable<Void> postMessage(MessageEntity message) {
        DatabaseReference databaseReference = database.child(CHILD_MESSAGES).child(message.getReceiverId());
        return create(databaseReference, message, null);
    }

    @Override
    public Observable<Void> editMessage(MessageEntity editedMessage) {
        DatabaseReference databaseReference = database.child(CHILD_MESSAGES).child(editedMessage.getReceiverId()).child(editedMessage.getId());
        return update(databaseReference, editedMessage, null);

    }

    @Override
    public Observable<Void> deleteMessage(MessageEntity message) {
        DatabaseReference databaseReference = database.child(CHILD_MESSAGES).child(message.getReceiverId()).child(message.getId());
        return delete(databaseReference, null);
    }
}
