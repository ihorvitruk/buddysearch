package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.UserDto;

import java.util.List;

import rx.Observable;

public interface UserRepository extends Repository {

    Observable<String> createUserIfNotExists(UserDto user, Messenger messenger);

    Observable<String> editUser(UserDto user, Messenger messenger);

    Observable<List<UserDto>> getUsers(Messenger messenger);

    Observable<UserDto> getUser(final String userId, Messenger messenger);

}
