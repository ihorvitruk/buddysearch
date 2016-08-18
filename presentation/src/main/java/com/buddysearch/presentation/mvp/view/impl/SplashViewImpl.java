package com.buddysearch.presentation.mvp.view.impl;

import android.app.Activity;

import com.buddysearch.presentation.mvp.view.SplashView;

public abstract class SplashViewImpl extends ViewImpl implements SplashView {
    public SplashViewImpl(Activity activity) {
        super(activity);
    }
}
