package com.buddysearch.android.presentation.mvp.presenter;

import com.buddysearch.android.domain.interactor.user.EditUser;
import com.buddysearch.android.library.data.manager.NetworkManager;
import com.buddysearch.android.library.presentation.DefaultSubscriber;
import com.buddysearch.android.library.presentation.mvp.presenter.BasePresenter;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.di.scope.ViewScope;
import com.buddysearch.android.presentation.mapper.UserDtoModelMapper;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.view.EditProfileView;

import javax.inject.Inject;

@ViewScope
public class EditProfilePresenter extends BasePresenter<EditProfileView> {

    private EditUser editUser;

    private UserDtoModelMapper userDtoModelMapper;

    @Inject
    public EditProfilePresenter(NetworkManager networkManager,
                                EditUser editUser,
                                UserDtoModelMapper userDtoModelMapper) {
        super(networkManager);
        this.editUser = editUser;
        this.userDtoModelMapper = userDtoModelMapper;
    }

    @Override
    public void refreshData(LoadDataType loadDataType) {
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
        editUser.unsubscribe();
    }

    public void updateUser(UserModel userModel) {
        editUser.execute(userDtoModelMapper.map1(userModel), new DefaultSubscriber<String>(view) {
            @Override
            public void onNext(String s) {
                super.onNext(s);
                view.showMessage(R.string.user_was_successfully_edited);
                view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMessage(e.getMessage());
                view.hideProgress();
            }
        });
    }
}
