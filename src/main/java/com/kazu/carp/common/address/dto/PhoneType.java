package com.kazu.carp.common.address.dto;

/**
 * Created by sarparda on 10.08.2016.
 */
public enum PhoneType {

    mobile("mobile"), home("home"), office("office"), fax("fax");

    private final String value;

    PhoneType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
