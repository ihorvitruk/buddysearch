package com.buddysearch.android.presentation.di.module;

import com.buddysearch.android.domain.Messenger;
import com.buddysearch.android.library.presentation.mvp.view.View;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.ViewScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private View view;

    public ViewModule(View view) {
        this.view = view;
    }

    @Provides
    @ViewScope
    Messenger providesMessenger() {
        return new Messenger() {
            @Override
            public void showNoNetworkMessage() {
                view.showMessage(R.string.no_internet_connection);
            }

            @Override
            public void showFromCacheMessage() {
                view.showMessage(R.string.data_from_cache);
            }
        };
    }
}
