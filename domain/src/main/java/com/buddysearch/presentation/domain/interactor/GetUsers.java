package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.dto.UserDto;
import com.buddysearch.presentation.domain.executor.PostExecutionThread;
import com.buddysearch.presentation.domain.executor.ThreadExecutor;
import com.buddysearch.presentation.domain.repository.UserRepository;

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
