package com.buddysearch.android.presentation.di.module;

import com.buddysearch.android.presentation.di.scope.ActivityScope;
import com.buddysearch.android.presentation.ui.activity.BaseDaggerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private BaseDaggerActivity activity;

    public ActivityModule(BaseDaggerActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    BaseDaggerActivity providesActivity() {
        return activity;
    }
}
