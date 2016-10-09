package com.buddysearch.android.domain.interactor;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.repository.Repository;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public abstract class UseCase<REQUEST_DATA, RESPONSE_DATA, REPOSITORY extends Repository> {

    protected final REPOSITORY repository;

    protected final Messenger messenger;

    private final Scheduler threadScheduler;

    private final Scheduler postExecutionScheduler;

    private CompositeSubscription subscription = new CompositeSubscription();

    public UseCase(REPOSITORY repository,
                   Messenger messenger,
                   @Named("Thread") Scheduler threadScheduler,
                   @Named("PostExecution") Scheduler postExecutionScheduler) {
        this.repository = repository;
        this.messenger = messenger;
        this.threadScheduler = threadScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    protected abstract Observable<RESPONSE_DATA> buildObservable(REQUEST_DATA requestData);

    public void execute(REQUEST_DATA requestData, Subscriber<RESPONSE_DATA> useCaseSubscriber) {
        this.subscription.add(this.buildObservable(requestData)
                .subscribeOn(threadScheduler)
                .observeOn(postExecutionScheduler)
                .subscribe(useCaseSubscriber));
    }

    public boolean isUnsubscribed() {
        return !subscription.hasSubscriptions();
    }

    public void unsubscribe() {
        if (!isUnsubscribed()) {
            subscription.clear();
        }
    }
}
