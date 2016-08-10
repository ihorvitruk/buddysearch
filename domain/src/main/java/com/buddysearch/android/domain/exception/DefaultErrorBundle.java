package com.buddysearch.android.domain.exception;

public class DefaultErrorBundle implements ErrorBundle {

    public static final String DEFAULT_ERROR_MSG = "Unknown error";

    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        return (getException() != null) ? this.exception.getMessage() : DEFAULT_ERROR_MSG;
    }
}
