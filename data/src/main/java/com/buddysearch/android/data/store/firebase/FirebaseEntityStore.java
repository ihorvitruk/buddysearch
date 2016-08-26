package com.buddysearch.android.data.store.firebase;

import com.buddysearch.android.library.data.entity.Entity;
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

    protected <T> Observable<T> get(Query query, Class<T> itemClass) {
        return getQuery(query, (subscriber, dataSnapshot) -> subscriber.onNext(extract(dataSnapshot, itemClass)));
    }

    protected <T> Observable<List<T>> getList(Query query, Class<T> itemClass) {
        return getQuery(query, (subscriber, dataSnapshot) -> subscriber.onNext(extractList(dataSnapshot, itemClass)));
    }

    protected <T extends Entity, R> Observable<R> create(DatabaseReference databaseReference, T value, R successResponse) {
        return postQuery(databaseReference, value, true, successResponse);
    }

    protected <T extends Entity, R> Observable<R> createIfNotExists(DatabaseReference databaseReference, T value, R successResponse) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            postQuery(databaseReference, value, false, successResponse)
                                    .subscribe(subscriber);
                        } else {
                            subscriber.onNext(successResponse);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        subscriber.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });
            }
        });
    }


    protected <T extends Entity, R> Observable<R> update(DatabaseReference databaseReference, T value, R successResponse) {
        return postQuery(databaseReference, value, false, successResponse);
    }

    protected <R> Observable<R> delete(DatabaseReference databaseReference, R successResponse) {
        return deleteQuery(databaseReference, successResponse);
    }

    private <T> Observable<T> getQuery(Query query, Action2<Subscriber<? super T>, DataSnapshot> onNextAction) {
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

    private <T extends Entity, R> Observable<R> postQuery(DatabaseReference databaseReference, T value, boolean newChild, R successResponse) {

        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                DatabaseReference reference = databaseReference;
                if (newChild) {
                    if (value.getId() == null) {
                        reference = databaseReference.push();
                        value.setId(reference.getKey());
                    } else {
                        reference = databaseReference.child(value.getId());
                    }
                }
                reference.setValue(value, (databaseError, databaseReference1) -> {
                    if (databaseError == null) {
                        subscriber.onNext(successResponse);
                    } else {
                        subscriber.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });
            }
        });
    }

    private <R> Observable<R> deleteQuery(DatabaseReference databaseReference, R successResponse) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                databaseReference.removeValue((databaseError, databaseReference1) -> {
                    if (databaseError == null) {
                        subscriber.onNext(successResponse);
                    } else {
                        subscriber.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });
            }
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
