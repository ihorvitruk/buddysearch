package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.repository.Repository;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

public abstract class UseCase1<RESPONSE_DATA, REPOSITORY extends Repository> extends
        UseCase<Void, RESPONSE_DATA, REPOSITORY> {

    public UseCase1(REPOSITORY repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<RESPONSE_DATA> buildObservable(Void aVoid) {
        return buildObservable();
    }

    protected abstract Observable<RESPONSE_DATA> buildObservable();

    public void execute(Subscriber<RESPONSE_DATA> useCaseSubscriber) {
        super.execute(null, useCaseSubscriber);
    }
}
