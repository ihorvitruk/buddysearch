package com.buddysearch.android.domain.interactor.message;

import com.buddysearch.android.domain.dto.MessageDto;
import com.buddysearch.android.domain.interactor.BaseUseCaseTest;
import com.buddysearch.android.domain.repository.MessagesRepository;

import rx.functions.Action0;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PostMessageTest extends BaseUseCaseTest<PostMessage, MessagesRepository> {

    private final MessageDto testMessage = new MessageDto();

    @Override
    protected PostMessage createUseCase() {
        return new PostMessage(mockRepository, mockThreadScheduler, mockPostExecutionScheduler);
    }

    @Override
    protected MessagesRepository createRepository() {
        return mock(MessagesRepository.class);
    }

    @Override
    public void testBuildUseCaseObservable() {
        testBuildUseCaseObservable(testMessage, new Action0() {
            @Override
            public void call() {
                verify(mockRepository).postMessage(testMessage);
            }
        });
    }
}
