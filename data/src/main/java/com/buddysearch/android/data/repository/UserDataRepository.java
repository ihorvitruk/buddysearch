package com.buddysearch.android.data.repository;

import com.buddysearch.android.data.mapper.UserEntityMapper;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.domain.dto.User;
import com.buddysearch.android.domain.repository.UserRepository;

import java.util.List;

import rx.Observable;

public class UserDataRepository extends BaseDataRepository<UserEntityStore, UserEntityMapper> implements UserRepository {

    public UserDataRepository(UserEntityStore dataStore, UserEntityMapper entityMapper) {
        super(dataStore, entityMapper);
    }

    @Override
    public Observable<List<User>> getUsers() {
        return dataStore.getUsers().map(userEntities -> entityMapper.transform(userEntities));
    }

    @Override
    public Observable<User> getUser(String userId) {
        return dataStore.getUser().map(userEntity -> entityMapper.transform(userEntity));
    }
}
