package com.buddysearch.android.data.store.firebase;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.data.manager.AuthManager;
import com.buddysearch.android.data.store.MessageEntityStore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class FirebaseMessageEntityStore extends FirebaseEntityStore implements MessageEntityStore {

    public static final String CHILD_MESSAGES = "messages";
    public static final String CHILD_USERS = "users";

    private AuthManager authManager;

    @Inject
    public FirebaseMessageEntityStore(AuthManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Observable<List<MessageEntity>> getMessages(String peerId) {
        Query query = database
                .child(CHILD_USERS)
                .child(authManager.getCurrentUserId())
                .child(CHILD_MESSAGES)
                .child(peerId);
        return getList(query, MessageEntity.class);
    }

    @Override
    public Observable<Void> postMessage(MessageEntity message) {
        DatabaseReference ref1 = database
                .child(CHILD_USERS)
                .child(message.getSenderId())
                .child(CHILD_MESSAGES)
                .child(message.getReceiverId());
        Observable o1 = create(ref1, message, null);

        DatabaseReference ref2 = database
                .child(CHILD_USERS)
                .child(message.getReceiverId())
                .child(CHILD_MESSAGES)
                .child(message.getSenderId());
        Observable o2 = create(ref2, message, null);

        return o1.mergeWith(o2);
    }

    @Override
    public Observable<Void> editMessage(MessageEntity editedMessage) {
        //Allow to edit only last message and only this last message is yours;
        DatabaseReference ref1 = database
                .child(CHILD_USERS)
                .child(editedMessage.getSenderId())
                .child(CHILD_MESSAGES)
                .child(editedMessage.getReceiverId())
                .child(editedMessage.getId());
        Observable o1 = update(ref1, editedMessage, null);

        DatabaseReference ref2 = database
                .child(CHILD_USERS)
                .child(editedMessage.getReceiverId())
                .child(CHILD_MESSAGES)
                .child(editedMessage.getSenderId())
                .child(editedMessage.getId());
        Observable o2 = update(ref2, editedMessage, null);

        return o1.mergeWith(o2);
    }

    @Override
    public Observable<Void> deleteMessage(MessageEntity message) {
        //We can delete message only for ourself. The peer will still see deleted
        //message because he use own messages store;
        String peerId = message.getSenderId().equals(authManager.getCurrentUserId())
                ? message.getReceiverId() : message.getSenderId();
        DatabaseReference databaseReference = database
                .child(CHILD_USERS)
                .child(authManager.getCurrentUserId())
                .child(CHILD_MESSAGES)
                .child(peerId)
                .child(message.getId());
        return delete(databaseReference, null);
    }
}
