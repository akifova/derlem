package com.kazu.carp.common.address.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Created by sarparda on 01.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CountyDto extends BaseDto {

    @Schema(title = "İlçe Adı")
    private String name;

    @Schema(title = "İlçe Kodu")
    private String countyCode;

    @Schema(hidden = true)
    private CityDto city;

    @Schema(hidden = true)
    private List<AddressDto> addresses;
}