package com.buddysearch.presentation.domain.interactor;

import com.buddysearch.presentation.domain.executor.PostExecutionThread;
import com.buddysearch.presentation.domain.executor.ThreadExecutor;
import com.buddysearch.presentation.domain.repository.Repository;

import rx.Observable;
import rx.Subscriber;

public abstract class UseCase1<RESPONSE_DATA, REPOSITORY extends Repository> extends
        UseCase<Void, RESPONSE_DATA, REPOSITORY> {
    public UseCase1(REPOSITORY repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(repository, threadExecutor, postExecutionThread);
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
