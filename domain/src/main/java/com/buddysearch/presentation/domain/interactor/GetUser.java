package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.dto.UserDto;
import com.buddysearch.presentation.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GetUser extends UseCase<String, UserDto, UserRepository> {

    @Inject
    public GetUser(UserRepository repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<UserDto> buildObservable(String userId) {
        return repository.getUser(userId);
    }
}
