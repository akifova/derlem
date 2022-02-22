package com.kazu.carp.security.account.exception;

/**
 * @author akifova
 * 10.04.2021
 */
public class PasswordIsNotCorrectException extends Exception {
    public PasswordIsNotCorrectException() {
        super();
    }

    public PasswordIsNotCorrectException(String message) {
        super(message);
    }

    public PasswordIsNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordIsNotCorrectException(Throwable cause) {
        super(cause);
    }
}