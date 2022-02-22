package com.kazu.carp.specimen.yarn.dto;

/**
 * @author akif.ova
 * @since 09.09.2020
 */
public enum SampleYarnUV {

    yes("yes"), no("no"), melting("melting");

    private final String value;

    SampleYarnUV(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
