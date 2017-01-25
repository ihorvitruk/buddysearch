package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.repository.Repository;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase1<RESULT, REPOSITORY extends Repository> extends
        UseCase<Void, RESULT, REPOSITORY> {

    public UseCase1(REPOSITORY repository,
                    Messenger messenger,
                    @Named("Thread") Scheduler threadScheduler,
                    @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, messenger, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<RESULT> buildObservable(Void aVoid) {
        return buildObservable();
    }

    protected abstract Observable<RESULT> buildObservable();

    public void execute(DisposableObserver<RESULT> observer) {
        super.execute(null, observer);
    }
}
