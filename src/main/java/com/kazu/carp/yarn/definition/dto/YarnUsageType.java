package com.kazu.carp.yarn.definition.dto;

public enum YarnUsageType {

    active("active"), passive("passive"), sample("sample");

    private final String value;

    YarnUsageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}