package com.buddysearch.android.domain.executor;

import rx.Scheduler;

public interface PostExecutionThread {

    Scheduler getScheduler();
}
