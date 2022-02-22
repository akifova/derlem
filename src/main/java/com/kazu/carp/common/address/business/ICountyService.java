package com.kazu.carp.common.address.business;

import com.kazu.carp.common.address.domain.County;
import com.kazu.carp.common.address.dto.CountyDto;

import java.util.List;

/**
 * Created by sarparda on 01.06.2017.
 */
public interface ICountyService {

    County getById(String countyId);

    List<CountyDto> search(String sp);
}
