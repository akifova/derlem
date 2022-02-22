package com.kazu.carp.app.exception.design;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CannotReadRgbException extends Exception {
    public CannotReadRgbException() { super("Cannot Read RGB Exception"); }
    public CannotReadRgbException(String message) { super(message); }
    public CannotReadRgbException(String message, Throwable cause) { super(message, cause); }
    public CannotReadRgbException(Throwable cause) { super(cause); }
}
