package com.kazu.carp.common.address.dto;

import com.kazu.carp.app.jpa.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//import com.sarparda.carp.ybt.dto.YbtDto;

/**
 * Created by sarparda on 24.05.2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class RegionDto extends BaseDto {

    private String name;
    private CountryDto country;
    private List<CityDto> cities;
}