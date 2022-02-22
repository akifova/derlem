package com.kazu.carp.design.archive.dto;

public enum ArchiveFileType {

    empty("empty"), scanning("scanning"), original("original");

    private final String value;

    ArchiveFileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
