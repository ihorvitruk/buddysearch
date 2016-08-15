package com.buddysearch.presentation.domain.executor;

import rx.Scheduler;

public interface PostExecutionThread {

    Scheduler getScheduler();
}
