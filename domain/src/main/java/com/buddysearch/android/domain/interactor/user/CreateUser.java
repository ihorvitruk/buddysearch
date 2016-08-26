package com.buddysearch.android.domain.interactor.user;

import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class CreateUser extends UseCase<UserDto, String, UserRepository> {

    @Inject
    public CreateUser(UserRepository repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<String> buildObservable(UserDto userDto) {
        return repository.createUserIfNotExists(userDto);
    }
}
