package com.kazu.carp.dashboard.archive.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akifova
 * 04.10.2021
 */
@Getter @Setter @Builder
public class ArchiveCountByStarRatingDto {
    private short starRating;
    private long count;

    public ArchiveCountByStarRatingDto(short starRating, long count) {
        this.starRating = starRating;
        this.count = count;
    }
}
