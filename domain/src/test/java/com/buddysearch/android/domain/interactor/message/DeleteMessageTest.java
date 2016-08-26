package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.MessageRepository;

import org.junit.Test;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteMessageTest extends BaseUseCaseTest<DeleteMessage, MessageRepository> {

    private final MessageDto testMessage = new MessageDto();

    @Override
    protected DeleteMessage createUseCase() {
        return new DeleteMessage(mockRepository, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected MessageRepository createRepository() {
        return mock(MessageRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(testMessage, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).deleteMessage(testMessage);
            }
        });
    }
}
