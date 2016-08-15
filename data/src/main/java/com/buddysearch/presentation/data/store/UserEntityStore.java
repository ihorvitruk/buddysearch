package com.buddysearch.presentation.data.store;

import com.buddysearch.presentation.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

public interface UserEntityStore extends EntityStore {

    Observable<List<UserEntity>> getUsers();

    Observable<UserEntity> getUser(String userId);
}
