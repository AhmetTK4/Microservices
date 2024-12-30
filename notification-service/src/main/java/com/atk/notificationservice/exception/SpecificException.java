package com.atk.notificationservice.exception;


public class SpecificException extends RuntimeException {

    public SpecificException(String message) {
        super(message);
    }

    public SpecificException(String message, Throwable cause) {
        super(message, cause);
    }
}
