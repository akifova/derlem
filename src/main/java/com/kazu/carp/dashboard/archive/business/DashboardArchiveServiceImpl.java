package com.kazu.carp.dashboard.archive.business;

import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDesignerDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByDifficultyDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStarRatingDto;
import com.kazu.carp.dashboard.archive.dto.ArchiveCountByStatusDto;
import com.kazu.carp.dashboard.archive.persistence.IDashboardArchiveDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author akifova
 * 04.10.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class DashboardArchiveServiceImpl implements IDashboardArchiveService {

    final IDashboardArchiveDao dao;

    public DashboardArchiveServiceImpl(IDashboardArchiveDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ArchiveCountByStatusDto> getCountByStatus() {
        return dao.getCountByStatus();
    }

    @Override
    public List<ArchiveCountByStarRatingDto> getCountByStarRating() {
        return dao.getCountByStarRating();
    }

    @Override
    public List<ArchiveCountByDifficultyDto> getCountByDifficulty() {
        return dao.getCountByDifficulty();
    }

    @Override
    public List<ArchiveCountByDesignerDto> getCountByDesigner() {
        return dao.getCountByDesigner();
    }
}