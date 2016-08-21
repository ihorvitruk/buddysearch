package com.buddysearch.android.data.repository;

import com.buddysearch.android.data.DataStatusMessenger;
import com.buddysearch.android.data.manager.NetworkManager;
import com.buddysearch.android.data.mapper.BaseEntityMapper;
import com.buddysearch.android.data.store.EntityStore;
import com.buddysearch.android.data.store.cache.Cache;
import com.buddysearch.android.domain.repository.Repository;

public abstract class BaseDataRepository
        <ENTITY_STORE extends EntityStore,
                CACHE extends Cache,
                ENTITY_MAPPER extends BaseEntityMapper> implements Repository {

    protected NetworkManager networkManager;

    protected DataStatusMessenger dataStatusMessenger;

    protected ENTITY_STORE cloudStore;

    protected CACHE cache;

    protected ENTITY_MAPPER entityMapper;

    public BaseDataRepository(NetworkManager networkManager,
                              DataStatusMessenger dataStatusMessenger,
                              ENTITY_STORE cloudStore,
                              CACHE cache,
                              ENTITY_MAPPER entityMapper) {
        this.networkManager = networkManager;
        this.dataStatusMessenger = dataStatusMessenger;
        this.cloudStore = cloudStore;
        this.cache = cache;
        this.entityMapper = entityMapper;
    }
}
