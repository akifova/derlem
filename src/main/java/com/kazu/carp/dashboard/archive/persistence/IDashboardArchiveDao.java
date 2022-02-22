package com.kazu.carp.dashboard.archive.persistence;

import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDesignerDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDifficultyDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStarRatingDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStatusDto;
import com.kazu.carp.design.archive.domain.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author akifova
 * 04.10.2021
 */
public interface IDashboardArchiveDao extends JpaRepository<Archive, String> {

    @Query("select new com.kazu.carp.dashboard.archive.dto.ArchiveCountByStatusDto(a.archiveStatus, count(a)) " +
            "from Archive a group by a.archiveStatus order by count(a) desc ")
    List<ArchiveCountByStatusDto> getCountByStatus();

    @Query("select new com.kazu.carp.dashboard.archive.dto.ArchiveCountByStarRatingDto(a.starRating, count(a)) " +
            "from Archive a group by a.starRating")
    List<ArchiveCountByStarRatingDto> getCountByStarRating();

    @Query("select new com.kazu.carp.dashboard.archive.dto.ArchiveCountByDifficultyDto(a.difficulty, count(a)) " +
            "from Archive a group by a.difficulty")
    List<ArchiveCountByDifficultyDto> getCountByDifficulty();

    @Query("select new com.kazu.carp.dashboard.archive.dto.ArchiveCountByDesignerDto(concat(a.designer.name, ' ' , a.designer.surname), count(a)) " +
            "from Archive a group by concat(a.designer.name, ' ' , a.designer.surname)")
    List<ArchiveCountByDesignerDto> getCountByDesigner();

}
