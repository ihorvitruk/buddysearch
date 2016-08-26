package com.buddysearch.android.data.repository;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.mapper.UserEntityDtoMapper;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.repository.UserRepository;
import com.buddysearch.android.library.data.DataStatusMessenger;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.repository.RepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class UserRepositoryImpl extends RepositoryImpl<UserEntityStore, UserCache, UserEntityDtoMapper> implements UserRepository {

    @Inject
    public UserRepositoryImpl(NetworkManager networkManager,
                              DataStatusMessenger dataStatusMessenger,
                              UserEntityStore cloudStore,
                              UserCache cache,
                              UserEntityDtoMapper entityDtoMapper) {
        super(networkManager, dataStatusMessenger, cloudStore, cache, entityDtoMapper);
    }

    @Override
    public Observable<String> createUserIfNotExists(UserDto user) {
        if (networkManager.isNetworkAvailable()) {
            return cloudStore.createUserIfNotExists(entityDtoMapper.map1(user));
        } else {
            return Observable.<String>empty().doOnCompleted(() -> dataStatusMessenger.showNoNetworkMessage());
        }
    }

    @Override
    public Observable<List<UserDto>> getUsers() {
        Observable<List<UserEntity>> entityObservable;
        if (networkManager.isNetworkAvailable()) {
            entityObservable = cloudStore.getUsers().doOnNext(userEntities -> cache.saveUsers(userEntities));
        } else {
            entityObservable = cache.getUsers().doOnNext(userEntities -> dataStatusMessenger.showFromCacheMessage());
        }
        return entityObservable.map(userEntities -> entityDtoMapper.map2(userEntities));
    }

    @Override
    public Observable<UserDto> getUser(String userId) {
        Observable<UserEntity> entityObservable;
        if (networkManager.isNetworkAvailable()) {
            entityObservable = cloudStore.getUser(userId).doOnNext(userEntity -> cache.saveUser(userId, userEntity));
        } else {
            entityObservable = cache.getUser(userId).doOnNext(userEntities -> dataStatusMessenger.showFromCacheMessage());
        }
        return entityObservable.map(userEntity -> entityDtoMapper.map2(userEntity));
    }
}
