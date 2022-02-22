package com.kazu.carp.dashboard.archive.business;

import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDesignerDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDifficultyDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStarRatingDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStatusDto;

import java.util.List;

/**
 * @author akifova
 * 04.10.2021
 */
public interface IDashboardArchiveService {
    List<ArchiveCountByStatusDto> getCountByStatus();

    List<ArchiveCountByStarRatingDto> getCountByStarRating();

    List<ArchiveCountByDifficultyDto> getCountByDifficulty();

    List<ArchiveCountByDesignerDto> getCountByDesigner();
}
