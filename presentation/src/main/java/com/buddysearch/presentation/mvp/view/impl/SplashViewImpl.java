package com.buddysearch.presentation.mvp.view.impl;

import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;
import com.buddysearch.presentation.mvp.view.SplashView;
import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;

public abstract class SplashViewImpl extends ViewImpl implements SplashView {
    public SplashViewImpl(BaseActivity activity) {
        super(activity);
    }
}
