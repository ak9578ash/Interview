package com.interview.preparation.low_level_design.testing.exception;

public class UrlFetchException extends RuntimeException {
    public UrlFetchException(String message) {
        super(message);
    }

    public UrlFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
