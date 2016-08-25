package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.MessagesRepository;

import org.junit.Test;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetMessageTest extends BaseUseCaseTest<GetMessage, MessagesRepository> {

    private final String FAKE_MESSAGE_ID = "1232522";

    @Override
    protected GetMessage createUseCase() {
        return new GetMessage(mockRepository, mockThreadScheduler, mockPostExecutionScheduler);
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
                verify(mockRepository).getMessage(FAKE_MESSAGE_ID);
            }
        });
    }
}
