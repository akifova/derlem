package com.kazu.carp.common.address.dto;

import com.kazu.carp.app.jpa.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by sarparda on 01.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class CityDto extends BaseDto {

    private String name;
    private String plateNumber;
    private CountryDto country;
    private RegionDto region;
    private List<CountyDto> counties;
}