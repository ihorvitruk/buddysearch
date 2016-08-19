package com.buddysearch.presentation.domain.repository;

import com.buddysearch.presentation.domain.dto.UserDto;

import java.util.List;

import rx.Observable;

public interface UserRepository extends Repository {

    Observable<List<UserDto>> getUsers();

    Observable<UserDto> getUser(final String userId);
}
