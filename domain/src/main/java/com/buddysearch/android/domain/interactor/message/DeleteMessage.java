package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.repository.MessageRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class DeleteMessage extends UseCase<MessageDto, Void, MessageRepository> {

    @Inject
    public DeleteMessage(MessageRepository repository,
                         Messenger messenger,
                         @Named("Thread") Scheduler threadScheduler,
                         @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, messenger, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<Void> buildObservable(MessageDto message) {
        return repository.deleteMessage(message, messenger);
    }
}
