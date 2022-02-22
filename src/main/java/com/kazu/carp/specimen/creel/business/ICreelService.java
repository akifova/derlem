package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.domain.Creel;
import com.kazu.carp.specimen.creel.dto.CreelDto;

import java.util.List;

/**
 * @author akifova
 * 07.05.2021
 */
public interface ICreelService {
    CreelDto create(CreelDto creelDto);

    void update(CreelDto creelDto);

    List<CreelDto> findAll();

    CreelDto findOne(String id);

    void delete(String id);

    void setCreelVersion(Creel creel, int qualityVersion, int paletteVersion);

    List<CreelDto> findByDesignPlanId(String designPlanId);

    Creel getById(String id);

    CreelDto findByArchiveId(String archiveId);
}
