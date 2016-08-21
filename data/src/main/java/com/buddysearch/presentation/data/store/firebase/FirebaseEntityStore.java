package com.buddysearch.presentation.data.store.firebase;

import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action2;
import rx.subscriptions.Subscriptions;

public abstract class FirebaseEntityStore {

    protected DatabaseReference database;

    public FirebaseEntityStore() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    protected <T> Observable<List<T>> getList(Query query, Class<T> itemClass) {
        return doQuery(query, (subscriber, dataSnapshot) -> subscriber.onNext(extractList(dataSnapshot, itemClass)));
    }

    protected <T> Observable<T> get(Query query, Class<T> itemClass) {
        return doQuery(query, (subscriber, dataSnapshot) -> subscriber.onNext(extract(dataSnapshot, itemClass)));
    }

    private <T> Observable<T> doQuery(Query query, Action2<Subscriber<? super T>, DataSnapshot> onNextAction) {
        return Observable.create(subscriber -> {
            ValueEventListener eventListener = query.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    onNextAction.call(subscriber, dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    subscriber.onError(new FirebaseException(databaseError.getMessage()));
                }
            });
            subscriber.add(Subscriptions.create(() -> query.removeEventListener(eventListener)));
        });
    }

    private <T> List<T> extractList(DataSnapshot dataSnapshot, Class<T> itemClass) {
        Iterable<DataSnapshot> items = dataSnapshot.getChildren();
        List<T> result = new ArrayList<>();
        for (DataSnapshot item : items) {
            result.add((extract(item, itemClass)));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private <T> T extract(DataSnapshot dataSnapshot, Class<T> clazz) {
        return dataSnapshot.getValue(clazz);
    }
}
