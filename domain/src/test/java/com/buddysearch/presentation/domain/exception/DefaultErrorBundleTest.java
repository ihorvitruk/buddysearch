package com.buddysearch.presentation.domain.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DefaultErrorBundleTest {

    @Test
    public void getErrorMessage_MockException_AsExceptionMessage() {
        Exception exception = mock(Exception.class);
        DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(exception);
        assertEquals(defaultErrorBundle.getErrorMessage(), exception.getMessage());
    }

    @Test
    public void getErrorMessage_NullException_AsDefaultErrorMessage() {
        DefaultErrorBundle defaultErrorBundle = new DefaultErrorBundle(null);
        assertEquals(defaultErrorBundle.getErrorMessage(), DefaultErrorBundle.DEFAULT_ERROR_MSG);
    }
}
