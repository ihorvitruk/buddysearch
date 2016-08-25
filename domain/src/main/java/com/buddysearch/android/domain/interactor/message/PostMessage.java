package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.repository.MessageRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class PostMessage extends UseCase<MessageDto, Boolean, MessageRepository> {

    @Inject
    public PostMessage(MessageRepository repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<Boolean> buildObservable(MessageDto messageDto) {
        return repository.postMessage(messageDto);
    }
}
