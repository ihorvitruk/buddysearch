package com.buddysearch.android.domain.repository;

import com.buddysearch.android.domain.interactor.UseCase;

public interface Repository {

    void register(UseCase useCase);

    void unregister(UseCase useCase);
}
