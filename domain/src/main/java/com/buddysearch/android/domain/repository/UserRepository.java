package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.dto.UserDto;

import java.util.List;

import rx.Observable;

public interface UserRepository extends Repository {

    Observable<String> createUserIfNotExists(UserDto user);

    Observable<List<UserDto>> getUsers();

    Observable<UserDto> getUser(final String userId);
}
