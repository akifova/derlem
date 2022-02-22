package com.kazu.carp.design.archive.dto;

public enum ArchiveStatus
{
    RD("RD", "Desen Çalışması"),
    SAMPLE("SAMPLE", "Numune"),
    PRODUCT("PRODUCT", "Üretim Arşivi"),
    STOCK("STOCK", "Stokta Saklanan"),
    UNUSABLE("UNUSABLE"," Kullanılmayacak"),
    RETIRED("RETIRED", "Emekli");

    private final String name;
    private final String description;

    ArchiveStatus(String value) {
        this.name = value;
        this.description = ArchiveStatus.valueOf(value).description;
    }

    ArchiveStatus(String value, String description) {
        this.name = value;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
