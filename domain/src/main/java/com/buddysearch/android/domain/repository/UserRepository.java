package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.entity.User;

import java.util.List;

import rx.Observable;

public interface UserRepository extends Repository {

    Observable<List<User>> getUsers();

    Observable<User> getUser(final String userId);
}
