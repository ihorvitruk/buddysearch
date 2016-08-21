package com.buddysearch.android.data.store;

import com.buddysearch.android.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

public interface UserEntityStore extends EntityStore {

    Observable<List<UserEntity>> getUsers();

    Observable<UserEntity> getUser(String userId);
}
