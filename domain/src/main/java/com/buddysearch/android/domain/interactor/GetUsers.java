package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GetUsers extends UseCase1<List<UserDto>, UserRepository> {

    @Inject
    public GetUsers(UserRepository repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<List<UserDto>> buildObservable() {
        return repository.getUsers();
    }
}
