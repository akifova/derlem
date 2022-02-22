package com.kazu.carp.common.file.util.dto;

public enum ModuleFolder {

    DESIGN_PLAN("DesignPlan"),
    DESIGN_ARCHIVE("DesignArchive"),
    STUFF("Stuff"),
    LABEL("Label"),
    BARCODE("Barcode");

    private final String value;

    ModuleFolder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
