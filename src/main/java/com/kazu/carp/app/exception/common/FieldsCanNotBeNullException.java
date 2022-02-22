package com.kazu.carp.app.exception.common;

import java.util.List;

/**
 * @author akifova
 * 07.07.2021
 */
public class FieldsCanNotBeNullException extends Exception {
    public FieldsCanNotBeNullException() {
        super();
    }

    public FieldsCanNotBeNullException(List<String> fields) {
        super("This Field(s) Can Not Be Null (" + String.join("- ", fields) + ")");
    }

    public FieldsCanNotBeNullException(List<String> fields, Throwable cause) {
        super("This Field(s) Can Not Be Null (" + String.join("- ", fields) + ")", cause);
    }

    public FieldsCanNotBeNullException(Throwable cause) {
        super(cause);
    }
}