package com.kazu.carp.dashboard.archive.dto;

import com.kazu.carp.design.archive.dto.ArchiveStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akifova
 * 04.10.2021
 */
@Getter @Setter @Builder
public class ArchiveCountByStatusDto {
    private ArchiveStatus status;
    private long count;

    public ArchiveCountByStatusDto(ArchiveStatus status, long count) {
        this.status = status;
        this.count = count;
    }
}
