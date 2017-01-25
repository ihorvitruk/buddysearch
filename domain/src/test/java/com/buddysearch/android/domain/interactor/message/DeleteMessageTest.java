package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.MessageRepository;

import org.junit.Test;

import io.reactivex.functions.Action;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteMessageTest extends BaseUseCaseTest<DeleteMessage, MessageRepository> {

    private final MessageDto testMessage = new MessageDto();

    @Override
    protected DeleteMessage createUseCase() {
        return new DeleteMessage(mockRepository, mockMessenger, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected MessageRepository createRepository() {
        return mock(MessageRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() throws Exception {
        testBuildUseCaseObservable(testMessage, new Action() {
            @Override
            public void run() throws Exception {
                verify(mockRepository).deleteMessage(testMessage, mockMessenger);
            }
        });
    }
}
