package com.buddysearch.android.data.store.cache;

import com.buddysearch.android.data.entity.UserEntity;
import com.buddysearch.android.data.store.UserEntityStore;
import com.buddysearch.android.library.data.store.cache.Cache;

import java.util.List;

public interface UserCache extends UserEntityStore, Cache {

    void saveUser(String userId, UserEntity userEntity);

    void saveUsers(List<UserEntity> userEntityList);
}
