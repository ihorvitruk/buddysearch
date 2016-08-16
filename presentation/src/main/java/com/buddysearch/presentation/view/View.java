package com.buddysearch.presentation.view;

public interface View {

    void showMessage(String message);

    void showProgress();

    void showProgress(String message);

    void showProgress(String message, String title);

    void hideProgress();
}
