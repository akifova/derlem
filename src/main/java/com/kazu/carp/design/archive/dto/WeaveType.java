package com.kazu.carp.design.archive.dto;

/**
 * @author akif.ova
 * @since 13.09.2020
 */
public enum WeaveType {

    FLAT("FLAT", "Düz Dokuma"),   //Düz Dokuma
    CROSS("CROSS", "Yatay Dokuma"); //Yatay Dokuma

    private final String value;
    private final String description;

    WeaveType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
