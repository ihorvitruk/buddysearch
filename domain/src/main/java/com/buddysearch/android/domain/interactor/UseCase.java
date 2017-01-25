package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.repository.Repository;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<PARAMS, RESULT, REPOSITORY extends Repository> {

    protected final REPOSITORY repository;

    protected final Messenger messenger;

    private final Scheduler threadScheduler;

    private final Scheduler postExecutionScheduler;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public UseCase(REPOSITORY repository,
                   Messenger messenger,
                   @Named("Thread") Scheduler threadScheduler,
                   @Named("PostExecution") Scheduler postExecutionScheduler) {
        this.repository = repository;
        this.messenger = messenger;
        this.threadScheduler = threadScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    protected abstract Observable<RESULT> buildObservable(PARAMS params);

    public void execute(PARAMS params, DisposableObserver<RESULT> observer) {
        final Observable<RESULT> observable = this.buildObservable(params)
                .subscribeOn(threadScheduler)
                .observeOn(postExecutionScheduler);
        addDisposable(observable.subscribeWith(observer));
        repository.register(this);
    }

    boolean isDisposed() {
        return !compositeDisposable.isDisposed();
    }

    public void dispose() {
        if (!isDisposed()) {
            compositeDisposable.dispose();
        }
        repository.unregister(this);
    }

    private void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
