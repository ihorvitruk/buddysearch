package com.buddysearch.presentation.domain.repository;

import com.buddysearch.presentation.domain.dto.User;

import java.util.List;

import rx.Observable;

public interface UserRepository extends Repository {

    Observable<List<User>> getUsers();

    Observable<User> getUser(final String userId);
}
