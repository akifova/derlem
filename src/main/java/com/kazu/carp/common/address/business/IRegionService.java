package com.kazu.carp.common.address.business;

import com.kazu.carp.common.address.domain.Region;
import com.kazu.carp.common.address.dto.RegionDto;

import java.util.List;

/**
 * Created by sarparda on 26.05.2017.
 */
public interface IRegionService {
    List<RegionDto> findAll();

    Region findOne(String regionId);

}
