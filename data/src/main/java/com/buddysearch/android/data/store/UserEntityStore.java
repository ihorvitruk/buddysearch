package com.buddysearch.android.data.store;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.library.data.store.EntityStore;

import java.util.List;

import io.reactivex.Observable;

public interface UserEntityStore extends EntityStore {

    Observable<String> createUserIfNotExists(UserEntity userEntity);

    Observable<String> editUser(UserEntity userEntity);

    Observable<List<UserEntity>> getUsers();

    Observable<UserEntity> getUser(String userId);
}
