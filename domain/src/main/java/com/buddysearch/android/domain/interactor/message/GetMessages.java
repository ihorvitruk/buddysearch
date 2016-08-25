package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.repository.MessageRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GetMessages extends UseCase<String, List<MessageDto>, MessageRepository> {

    @Inject
    public GetMessages(MessageRepository repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<List<MessageDto>> buildObservable(String peerId) {
        return repository.getMessages(peerId);
    }
}
