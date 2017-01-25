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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;

public abstract class FirebaseEntityStore {

    protected DatabaseReference database;

    public FirebaseEntityStore() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    protected <T> Observable<T> get(Query query, Class<T> itemClass, boolean subscribeForSingleEvent) {
        return getQuery(query, (subscriber, dataSnapshot) -> subscriber.onNext(extract(dataSnapshot, itemClass)), subscribeForSingleEvent);
    }

    protected <T> Observable<List<T>> getList(Query query, Class<T> itemClass, boolean subscribeForSingleEvent) {
        return getQuery(query, (subscriber, dataSnapshot) -> subscriber.onNext(extractList(dataSnapshot, itemClass)), subscribeForSingleEvent);
    }

    protected <T extends Entity, R> Observable<R> create(DatabaseReference databaseReference, T value, R successResponse) {
        return postQuery(databaseReference, value, true, successResponse);
    }

    protected <T extends Entity, R> Observable<R> createIfNotExists(DatabaseReference databaseReference, T value, R successResponse) {
        return Observable.create(new ObservableOnSubscribe<R>() {
            @Override
            public void subscribe(ObservableEmitter<R> emitter) throws Exception {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            postQuery(databaseReference, value, false, successResponse).subscribeWith(new Observer<R>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(R r) {
                                    emitter.onNext(r);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    emitter.onError(e);
                                }

                                @Override
                                public void onComplete() {
                                    emitter.onComplete();
                                }
                            });
                        } else {
                            emitter.onNext(successResponse);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(new FirebaseException(databaseError.getMessage()));
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

    private <T> Observable<T> getQuery(Query query, BiConsumer<ObservableEmitter<? super T>, DataSnapshot> consumer, boolean subscribeForSingleEvent) {
        return Observable.create(emitter -> {
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        consumer.accept(emitter, dataSnapshot);
                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    emitter.onError(new FirebaseException(databaseError.getMessage()));
                }
            };
            if (subscribeForSingleEvent) {
                query.addListenerForSingleValueEvent(eventListener);
            } else {
                query.addValueEventListener(eventListener);
            }
            // subscriber.add(Subscriptions.create(() -> query.removeEventListener(eventListener)));
        });
    }

    private <T extends Entity, R> Observable<R> postQuery(DatabaseReference databaseReference, T value, boolean newChild, R successResponse) {

        return Observable.create(new ObservableOnSubscribe<R>() {
            @Override
            public void subscribe(ObservableEmitter<R> e) throws Exception {
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
                        e.onNext(successResponse);
                    } else {
                        e.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });
            }
        });
    }

    private <R> Observable<R> deleteQuery(DatabaseReference databaseReference, R successResponse) {
        return Observable.create(e -> databaseReference.removeValue((databaseError, databaseReference1) -> {
            if (databaseError == null) {
                e.onNext(successResponse);
            } else {
                e.onError(new FirebaseException(databaseError.getMessage()));
            }
        }));
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
