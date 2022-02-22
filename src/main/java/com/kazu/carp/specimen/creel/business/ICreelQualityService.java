package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.domain.CreelQuality;
import com.kazu.carp.specimen.creel.dto.CreelQualityDto;

import java.util.List;

/**
 * @author akifova
 * 09.05.2021
 */
public interface ICreelQualityService {
    CreelQualityDto update(CreelQualityDto creelQualityDto);

    List<CreelQualityDto> findByCreelId(String creelId);

    CreelQualityDto findOne(String id);

    CreelQuality getById(String id);
}
