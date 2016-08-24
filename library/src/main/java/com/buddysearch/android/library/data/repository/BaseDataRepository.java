package com.buddysearch.android.library.data.repository;

import com.buddysearch.android.domain.repository.Repository;
import com.buddysearch.android.library.data.DataStatusMessenger;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.data.mapper.BaseMapper;
import com.buddysearch.android.library.data.store.EntityStore;
import com.buddysearch.android.library.data.store.cache.Cache;

public abstract class BaseDataRepository
        <ENTITY_STORE extends EntityStore,
                CACHE extends Cache,
                ENTITY_MAPPER extends BaseMapper> implements Repository {

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
