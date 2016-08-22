package com.buddysearch.presentation.util;

import com.buddysearch.presentation.mvp.view.View;

import rx.Subscriber;

public class DefaultSubscriber<T> extends Subscriber<T> {

    private View view;

    public DefaultSubscriber(View view) {
        this.view = view;
    }

    @Override
    public void onCompleted() {
        view.hideProgress();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
