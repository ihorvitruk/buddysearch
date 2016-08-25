package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.MessageRepository;

import org.junit.Test;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetMessagesTest extends BaseUseCaseTest<GetMessages, MessageRepository> {

    private final String FAKE_PEER_ID = "32215215";

    @Override
    protected GetMessages createUseCase() {
        return new GetMessages(mockRepository, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected MessageRepository createRepository() {
        return mock(MessageRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(FAKE_PEER_ID, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).getMessages(FAKE_PEER_ID);
            }
        });
    }
}
