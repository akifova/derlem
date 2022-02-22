package com.kazu.carp.common.address.dto;

import com.kazu.carp.app.jpa.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by sarparda on 02.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class CountryDto extends BaseDto {

    private String name;
    private String numericCode;
    private String alpha2Code;
    private String alpha3Code;
    private List<RegionDto> regions;
    private List<CityDto> cities;
}