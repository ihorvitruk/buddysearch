package com.buddysearch.android.library.presentation;

import com.buddysearch.android.library.presentation.mvp.view.View;

import io.reactivex.observers.DisposableObserver;

public class DefaultObserver<T> extends DisposableObserver<T> {

    private View view;

    public DefaultObserver(View view) {
        this.view = view;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        if (view != null) {
            view.hideProgress();
        }
    }


}
