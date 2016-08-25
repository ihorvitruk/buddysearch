package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.repository.MessagesRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class EditMessage extends UseCase<EditMessageRequestModel, Boolean, MessagesRepository> {

    @Inject
    public EditMessage(MessagesRepository repository, @Named("Thread") Scheduler threadScheduler, @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<Boolean> buildObservable(EditMessageRequestModel editMessageRequestModel) {
        return repository.editMessage(editMessageRequestModel.getMessageId(), editMessageRequestModel.getMessageDto());
    }
}
