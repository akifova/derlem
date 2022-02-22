package com.kazu.carp.app.exception.design;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author akifova
 * 12.02.2021
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CannotMatchRgbException extends Exception {
    public CannotMatchRgbException() { super("Cannot Match Rgb Exception"); }
    public CannotMatchRgbException(String message) { super(message); }
    public CannotMatchRgbException(String message, Throwable cause) { super(message, cause); }
    public CannotMatchRgbException(Throwable cause) { super(cause); }
}
