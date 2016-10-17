package com.buddysearch.android.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.EditText;

import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivityEditProfileBinding;
import com.buddysearch.android.presentation.di.component.ViewComponent;
import com.buddysearch.android.presentation.mvp.model.UserModel;
import com.buddysearch.android.presentation.mvp.presenter.EditProfilePresenter;
import com.buddysearch.android.presentation.mvp.view.EditProfileView;
import com.buddysearch.android.presentation.mvp.view.impl.EditProfileViewImpl;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMax;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

public class EditProfileActivity extends BaseDaggerActivity<EditProfileView, EditProfilePresenter, ActivityEditProfileBinding> {

    @Inject
    Lazy<EditProfilePresenter> editProfilePresenter;

    @NotEmpty
    EditText etFirstName;

    @NotEmpty
    EditText etLastName;

    @DecimalMax(100)
    EditText etAge;

    @InjectExtra
    UserModel user;

    private Validator validator;

    @Override
    public void onLoadFinished() {
        super.onLoadFinished();
        Dart.inject(this);
        validator = initValidator();
        initUi();
    }

    public static void start(Context context, UserModel userModel) {
        if (userModel == null) {
            return;
        }
        Intent intent = Henson.with(context)
                .gotoEditProfileActivity()
                .user(userModel)
                .build();
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
        ActivityEditProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        return binding;
    }

    private void initUi() {
        initViewsForSaripaar();
        initSaveButton();
        initEditTexts();
    }

    private void initSaveButton() {
        binding.btnSave.setOnClickListener(v -> validator.validate());
    }

    private void initViewsForSaripaar() {
        etFirstName = binding.etFirstName;
        etLastName = binding.etLastName;
        etAge = binding.etAge;
    }

    private void initEditTexts() {
        if (user == null) {
            return;
        }
        binding.etFirstName.setText(user.getFirstName());
        binding.etLastName.setText(user.getLastName());
        binding.etAge.setText(String.valueOf(user.getAge()));
    }

    private Validator initValidator() {
        Validator validator = new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                view.hideKeyboard();
                view.showMessage("saved");
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    View view1 = error.getView();
                    String message = error.getCollatedErrorMessage(EditProfileActivity.this);

                    if (view1 instanceof EditText) {
                        ((EditText) view1).setError(message);
                    } else {
                        EditProfileActivity.this.view.showMessage(message);
                    }
                }
            }
        });
        return validator;
    }
}
