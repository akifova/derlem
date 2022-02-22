package com.kazu.carp.app.exception.app;

/**
 * @author akifova
 * 29.10.2021
 */
public class InvalidLicenseException extends Exception {
    public InvalidLicenseException() {
        super();
    }

    public InvalidLicenseException(String message) {
        super(message);
    }

    public InvalidLicenseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLicenseException(Throwable cause) {
        super(cause);
    }
}