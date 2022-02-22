package com.kazu.carp.common.address.business;

import com.kazu.carp.common.address.domain.Country;
import com.kazu.carp.common.address.dto.CountryDto;

import java.util.List;

public interface ICountryService {

    CountryDto findOne(String id);

    Country getById(String id);;

    List<CountryDto> search(String sp);
}
