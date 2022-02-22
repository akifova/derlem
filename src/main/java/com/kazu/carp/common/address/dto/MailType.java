package com.kazu.carp.common.address.dto;

/**
 * Created by sarparda on 10.08.2016.
 */
public enum MailType {

    individual("individual"), work("work");

    private final String value;

    MailType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
