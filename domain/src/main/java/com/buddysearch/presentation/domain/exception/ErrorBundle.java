package com.buddysearch.presentation.domain.exception;

public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
