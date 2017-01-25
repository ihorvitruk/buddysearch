package com.buddysearch.android.domain.interactor.user;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.domain.dto.UserDto;
import com.buddysearch.android.domain.interactor.UseCase;
import com.buddysearch.android.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class EditUser extends UseCase<UserDto, String, UserRepository> {

    @Inject
    public EditUser(UserRepository repository,
                    Messenger messenger,
                    @Named("Thread") Scheduler threadScheduler,
                    @Named("PostExecution") Scheduler postExecutionScheduler) {
        super(repository, messenger, threadScheduler, postExecutionScheduler);
    }

    @Override
    protected Observable<String> buildObservable(UserDto userDto) {
        return repository.editUser(userDto, messenger);
    }
}
