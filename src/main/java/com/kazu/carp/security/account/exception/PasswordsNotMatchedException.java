package com.kazu.carp.security.account.exception;

/**
 * @author akifova
 * 10.04.2021
 */
public class PasswordsNotMatchedException extends Exception {
    public PasswordsNotMatchedException() {
        super();
    }

    public PasswordsNotMatchedException(String message) {
        super(message);
    }

    public PasswordsNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordsNotMatchedException(Throwable cause) {
        super(cause);
    }
}