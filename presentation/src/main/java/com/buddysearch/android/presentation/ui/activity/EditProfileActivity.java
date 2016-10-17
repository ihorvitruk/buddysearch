package com.buddysearch.android.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.buddysearch.android.library.presentation.ui.activity.BaseActivity;
import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivityEditProfileBinding;
import com.buddysearch.android.presentation.di.component.ViewComponent;
import com.buddysearch.android.presentation.mvp.presenter.EditProfilePresenter;
import com.buddysearch.android.presentation.mvp.view.EditProfileView;
import com.buddysearch.android.presentation.mvp.view.impl.EditProfileViewImpl;

import javax.inject.Inject;

import dagger.Lazy;

public class EditProfileActivity extends BaseDaggerActivity<EditProfileView, EditProfilePresenter, ActivityEditProfileBinding> {

    @Inject
    Lazy<EditProfilePresenter> editProfilePresenter;

    public static void start(Context context, boolean clearStack) {
        Intent intent = BaseActivity.getBaseStartIntent(context, EditProfileActivity.class, clearStack);
        context.startActivity(intent);
    }

    @Override
    protected void injectViewComponent(ViewComponent viewComponent) {
        viewComponent.inject(this);
    }

    @Override
    protected EditProfileView initView() {
        return new EditProfileViewImpl(this) {
        };
    }

    @Override
    protected Lazy<EditProfilePresenter> initPresenter() {
        return editProfilePresenter;
    }

    @Override
    protected ActivityEditProfileBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
    }
}
