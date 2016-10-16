package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.di.scope.ViewScope;
import com.buddysearch.android.presentation.mvp.view.EditProfileView;

import javax.inject.Inject;

/**
 * Created by ihor on 10/16/16.
 */

@ViewScope
public class EditProfilePresenter extends BasePresenter<EditProfileView> {

    @Inject
    public EditProfilePresenter(NetworkManager networkManager) {
        super(networkManager);
    }

    @Override
    public void refreshData() {
    }
}
