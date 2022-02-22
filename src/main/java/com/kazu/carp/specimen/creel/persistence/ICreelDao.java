package com.kazu.carp.specimen.creel.persistence;

import com.kazu.carp.specimen.creel.domain.Creel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 07.05.2021
 */
public interface ICreelDao extends JpaRepository<Creel, String> {
    List<Creel> findByDesignPlanId(String designPlanId);

    Creel findByArchivesId(String archiveId);
}
