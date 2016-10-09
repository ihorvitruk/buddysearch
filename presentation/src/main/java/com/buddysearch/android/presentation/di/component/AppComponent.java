package com.buddysearch.android.presentation.di.component;

import com.buddysearch.android.presentation.App;
import com.buddysearch.android.presentation.di.module.AppModule;
import com.buddysearch.android.presentation.di.module.CacheModule;
import com.buddysearch.android.presentation.di.module.EntityStoreModule;
import com.buddysearch.android.presentation.di.module.RepositoryModule;
import com.buddysearch.android.presentation.di.module.ViewModule;
import com.buddysearch.android.presentation.di.scope.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class,
        RepositoryModule.class,
        EntityStoreModule.class,
        CacheModule.class})
public interface AppComponent {

    ViewComponent plus(ViewModule viewModule);

    void inject(App app);
}
