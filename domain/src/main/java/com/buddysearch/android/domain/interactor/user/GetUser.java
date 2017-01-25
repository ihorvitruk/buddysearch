package com.buddysearch.android.domain.interactor.user;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.listener.OnUserChangedListener;
import com.buddysearch.android.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import lombok.Setter;

public class GetUser extends UseCase<String, UserDto, UserRepository> implements OnUserChangedListener {

    @Setter
    private OnUserChangedListener onUserChangedListener;

    @Inject
    public GetUser(UserRepository repository,
                   Messenger messenger,
                   @Named("Thread") Scheduler threadScheduler,
                   @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, messenger, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<UserDto> buildObservable(String userId) {
        return repository.getUser(userId, messenger);
    }

    @Override
    public void onDataChanged(String userId) {
        if (onUserChangedListener != null) {
            onUserChangedListener.onDataChanged(userId);
        }
    }
}
