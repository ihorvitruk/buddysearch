package com.buddysearch.presentation.data.repository;

import com.buddysearch.presentation.data.mapper.UserEntityMapper;
import com.buddysearch.presentation.data.store.UserEntityStore;
import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.domain.repository.UserRepository;

import java.util.List;

import rx.Observable;

public class UserDataRepository extends BaseDataRepository<UserEntityStore, UserEntityMapper> implements UserRepository {

    public UserDataRepository(UserEntityStore dataStore, UserEntityMapper entityMapper) {
        super(dataStore, entityMapper);
    }

    @Override
    public Observable<List<User>> getUsers() {
        return dataStore.getUsers().map(userEntities -> entityMapper.map(userEntities));
    }

    @Override
    public Observable<User> getUser(String userId) {
        return dataStore.getUser(userId).map(userEntity -> entityMapper.map(userEntity));
    }
}
