package com.kazu.carp.dashboard.archive.presentation;

import com.kazu.carp.dashboard.archive.business.IDashboardArchiveService;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDesignerDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDifficultyDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStarRatingDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author akifova
 * 04.10.2021
 */
@RestController
@RequestMapping("/dashboard/archive")
public class DashboardArchiveController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardArchiveController.class);

    final IDashboardArchiveService dashboardArchiveService;

    public DashboardArchiveController(IDashboardArchiveService dashboardArchiveService) {
        this.dashboardArchiveService = dashboardArchiveService;
    }

    @GetMapping("/count/by-status")
    public List<ArchiveCountByStatusDto> archiveCountByStatus() {
        return dashboardArchiveService.getCountByStatus();
    }

    @GetMapping("/count/by-star-rating")
    public List<ArchiveCountByStarRatingDto> archiveCountByStarRating() {
        return dashboardArchiveService.getCountByStarRating();
    }

    @GetMapping("/count/by-difficulty")
    public List<ArchiveCountByDifficultyDto> archiveCountByDifficulty() {
        return dashboardArchiveService.getCountByDifficulty();
    }

    @GetMapping("/count/by-designer")
    public List<ArchiveCountByDesignerDto> archiveCountByDesigner() {
        return dashboardArchiveService.getCountByDesigner();
    }
}