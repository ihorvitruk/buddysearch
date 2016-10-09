package com.buddysearch.android.library.data.repository;

import com.buddysearch.android.domain.repository.Repository;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.mapper.BaseMapper;
import com.buddysearch.android.library.data.store.EntityStore;
import com.buddysearch.android.library.data.store.cache.Cache;

public abstract class RepositoryImpl
        <ENTITY_STORE extends EntityStore,
                CACHE extends Cache,
                ENTITY_DTO_MAPPER extends BaseMapper> implements Repository {

    protected NetworkManager networkManager;

    protected ENTITY_STORE cloudStore;

    protected CACHE cache;

    protected ENTITY_DTO_MAPPER entityDtoMapper;

    public RepositoryImpl(NetworkManager networkManager,
                          ENTITY_STORE cloudStore,
                          CACHE cache,
                          ENTITY_DTO_MAPPER entityDtoMapper) {
        this.networkManager = networkManager;
        this.cloudStore = cloudStore;
        this.cache = cache;
        this.entityDtoMapper = entityDtoMapper;
    }
}
