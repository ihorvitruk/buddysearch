package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.MessagesRepository;

import org.junit.Test;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EditMessageTest extends BaseUseCaseTest<EditMessage, MessagesRepository> {

    private final String FAKE_MESSAGE_ID = "12345";

    private final MessageDto testMessage = new MessageDto();

    @Override
    protected EditMessage createUseCase() {
        return new EditMessage(mockRepository, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected MessagesRepository createRepository() {
        return mock(MessagesRepository.class);
    }

    @Test
    @Override
    public void testBuildUseCaseObservable() {
        EditMessageRequestModel editMessageRequestModel
                = new EditMessageRequestModel(FAKE_MESSAGE_ID, testMessage);
        testBuildUseCaseObservable(editMessageRequestModel, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).editMessage(FAKE_MESSAGE_ID, testMessage);
            }
        });
    }
}
