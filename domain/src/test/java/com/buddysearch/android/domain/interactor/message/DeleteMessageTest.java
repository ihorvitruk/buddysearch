package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.MessagesRepository;

import org.junit.Test;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteMessageTest extends BaseUseCaseTest<DeleteMessage, MessagesRepository> {

    private final String FAKE_MESSAGE_ID = "23232";

    @Override
    protected DeleteMessage createUseCase() {
        return new DeleteMessage(mockRepository, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected MessagesRepository createRepository() {
        return mock(MessagesRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(FAKE_MESSAGE_ID, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).deleteMessage(FAKE_MESSAGE_ID);
            }
        });
    }
}
