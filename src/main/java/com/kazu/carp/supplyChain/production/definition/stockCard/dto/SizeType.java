package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

public enum SizeType {
    standard("standard"), roll("roll");

    private final String value;

    SizeType(String value){ this.value = value; }

    public String getValue() { return value; }
}
