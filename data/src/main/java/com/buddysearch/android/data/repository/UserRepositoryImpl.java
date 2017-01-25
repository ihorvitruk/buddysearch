package com.buddysearch.android.data.repository;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.mapper.UserEntityDtoMapper;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.data.store.cache.UserCache;
import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.listener.OnUserChangedListener;
import com.buddysearch.android.domain.repository.UserRepository;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.repository.RepositoryImpl;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRepositoryImpl extends RepositoryImpl<UserEntityStore, UserCache, UserEntityDtoMapper>
        implements UserRepository, OnUserChangedListener {

    @Inject
    public UserRepositoryImpl(NetworkManager networkManager,
                              UserEntityStore cloudStore,
                              UserCache cache,
                              UserEntityDtoMapper entityDtoMapper) {
        super(networkManager, cloudStore, cache, entityDtoMapper);
    }

    @Override
    public Observable<String> createUserIfNotExists(UserDto user, Messenger messenger) {
        if (networkManager.isNetworkAvailable()) {
            return cloudStore.createUserIfNotExists(entityDtoMapper.map1(user));
        } else {
            return Observable.<String>empty().doOnComplete(messenger::showNoNetworkMessage);
        }
    }

    @Override
    public Observable<String> editUser(UserDto user, Messenger messenger) {
        if (networkManager.isNetworkAvailable()) {
            return cloudStore.editUser(entityDtoMapper.map1(user)).doOnNext(this::onDataChanged);
        } else {
            return Observable.<String>empty().doOnComplete(messenger::showNoNetworkMessage);
        }
    }

    @Override
    public Observable<List<UserDto>> getUsers(Messenger messenger) {
        Observable<List<UserEntity>> entityObservable;
        if (networkManager.isNetworkAvailable()) {
            entityObservable = cloudStore.getUsers().doOnNext(userEntities -> cache.saveUsers(userEntities));
        } else {
            entityObservable = cache.getUsers().doOnNext(userEntities -> messenger.showFromCacheMessage());
        }
        return entityObservable.map(userEntities -> entityDtoMapper.map2(userEntities));
    }

    @Override
    public Observable<UserDto> getUser(String userId, Messenger messenger) {
        Observable<UserEntity> entityObservable;
        if (networkManager.isNetworkAvailable()) {
            entityObservable = cloudStore.getUser(userId).doOnNext(userEntity -> cache.saveUser(userId, userEntity));
        } else {
            entityObservable = cache.getUser(userId).doOnNext(userEntities -> messenger.showFromCacheMessage());
        }
        return entityObservable.map(userEntity -> entityDtoMapper.map2(userEntity));
    }

    @Override
    public void onDataChanged(String userId) {
        Collection<UseCase> useCasesList = useCasesMap.values();
        for (UseCase useCase : useCasesList) {
            if (useCase instanceof OnUserChangedListener) {
                ((OnUserChangedListener) useCase).onDataChanged(userId);
            }
        }
    }
}
