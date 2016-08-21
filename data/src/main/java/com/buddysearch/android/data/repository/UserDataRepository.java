package com.buddysearch.android.data.repository;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.android.data.mapper.UserEntityMapper;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class UserDataRepository extends BaseDataRepository<UserEntityStore, UserCache, UserEntityMapper> implements UserRepository {

    @Inject
    public UserDataRepository(NetworkManager networkManager,
                              UserEntityStore cloudStore,
                              UserCache cache,
                              UserEntityMapper entityMapper) {
        super(networkManager, cloudStore, cache, entityMapper);
    }

    @Override
    public Observable<List<UserDto>> getUsers() {
        Observable<List<UserEntity>> entityObservable;
        if (networkManager.isNetworkAvailable()) {
            entityObservable = cloudStore.getUsers().doOnNext(userEntities -> cache.saveUsers(userEntities));
        } else {
            entityObservable = cache.getUsers();
        }
        return entityObservable.map(userEntities -> entityMapper.map(userEntities));
    }

    @Override
    public Observable<UserDto> getUser(String userId) {
        Observable<UserEntity> entityObservable;
        if (networkManager.isNetworkAvailable()) {
            entityObservable =
        }
        return cloudStore.getUser(userId).map(userEntity -> entityMapper.map(userEntity));
    }
}
