package com.kazu.carp.dashboard.archive.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akifova
 * 04.10.2021
 */
@Getter @Setter @Builder
public class ArchiveCountByDesignerDto {
    private String designer;
    private long count;

    public ArchiveCountByDesignerDto(String designer, long count) {
        this.designer = designer;
        this.count = count;
    }
}
