package com.kazu.carp.yarn.definition.dto;

public enum YarnActionType {

    fifo("fifo"),
    lifo("lifo"),
    siro("siro"); // Rastgele

    private final String value;

    YarnActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
