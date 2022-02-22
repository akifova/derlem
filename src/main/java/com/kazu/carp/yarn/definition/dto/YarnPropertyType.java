package com.kazu.carp.yarn.definition.dto;

public enum YarnPropertyType {

    other("other"),
    color("color"),
    size("size"),
    fixed("fixed"); //Fikse Tipi

    private final String value;

    YarnPropertyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
