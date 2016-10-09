package com.buddysearch.android.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.mvp.view.SplashView;

public abstract class SplashViewImpl extends ViewImpl implements SplashView {
    public SplashViewImpl(BaseActivity activity) {
        super(activity);
    }
}
