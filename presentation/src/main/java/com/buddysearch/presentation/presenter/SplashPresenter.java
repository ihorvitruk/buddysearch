package com.buddysearch.presentation.presenter;

import com.buddysearch.presentation.di.scope.ActivityScope;
import com.buddysearch.presentation.domain.dto.User;
import com.buddysearch.presentation.domain.interactor.DefaultSubscriber;
import com.buddysearch.presentation.domain.interactor.GetUsers;
import com.buddysearch.presentation.view.SplashView;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashView> {

    private GetUsers getUsers;

    @Inject
    public SplashPresenter(GetUsers getUsers) {
        this.getUsers = getUsers;
    }

    @Override
    protected void onViewAttached() {
        refreshData();
    }

    @Override
    protected void onViewDetached() {
        getUsers.unsubscribe();
    }

    @Override
    public void refreshData() {
        getUsers();
    }

    private void getUsers() {
        getUsers.execute(null, new DefaultSubscriber<List<User>>() {
            @Override
            public void onNext(List<User> users) {
                super.onNext(users);
                view.renderUsers(null);
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.hideProgress();
                view.showMessage(e.getMessage());
            }
        });
    }
}
