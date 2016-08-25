package com.buddysearch.android.data.store.firebase;

import com.buddysearch.android.data.entity.MessageEntity;
import com.buddysearch.android.data.store.MessageEntityStore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class FirebaseMessageEntityStore extends FirebaseEntityStore implements MessageEntityStore {

    public static final String CHILD_MESSAGES = "users";

    @Inject
    public FirebaseMessageEntityStore() {
    }

    @Override
    public Observable<Boolean> postMessage(MessageEntity message) {
        return null;
    }

    @Override
    public Observable<List<MessageEntity>> getMessages(String peerUserId) {
        Query query = database.child(CHILD_MESSAGES)
                .child("senderId")
                .equalTo(peerUserId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.child()
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return getList(query, MessageEntity.class);
    }

    @Override
    public Observable<Boolean> editMessage(String messageId, MessageEntity newMessage) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteMessage(String messageId) {
        return null;
    }
}
