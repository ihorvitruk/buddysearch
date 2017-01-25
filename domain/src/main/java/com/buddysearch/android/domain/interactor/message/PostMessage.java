package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.repository.MessageRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class PostMessage extends UseCase<MessageDto, Void, MessageRepository> {

    @Inject
    public PostMessage(MessageRepository repository,
                       Messenger messenger,
                       @Named("Thread") Scheduler threadScheduler,
                       @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, messenger, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<Void> buildObservable(MessageDto messageDto) {
        return repository.postMessage(messageDto, messenger);
    }
}
