package com.kazu.carp.specimen.creel.dto;

/**
 * @author akif.ova
 * @since 08.09.2020
 */
public enum Raising {

    yes("yes"), no("no");

    private final String value;

    Raising(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
