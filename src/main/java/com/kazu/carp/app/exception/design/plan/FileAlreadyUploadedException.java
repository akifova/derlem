package com.kazu.carp.app.exception.design.plan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author akifova
 * 14.02.2021
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class FileAlreadyUploadedException extends Exception {
    public FileAlreadyUploadedException() {
        super("File Already Uploaded Exception");
    }

    public FileAlreadyUploadedException(String message) {
        super(message);
    }

    public FileAlreadyUploadedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileAlreadyUploadedException(Throwable cause) {
        super(cause);
    }
}