package com.kazu.carp.dashboard.archive.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author akifova
 * 04.10.2021
 */
@Getter @Setter
public class ArchiveCountByDifficultyDto {
    private int difficulty;
    private long count;

    public ArchiveCountByDifficultyDto(int difficulty, long count) {
        this.difficulty = difficulty;
        this.count = count;
    }
}
