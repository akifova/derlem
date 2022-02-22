package com.kazu.carp.common.firm.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.common.firm.domain.Firm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author akifova
 * 29.04.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface IFirmMapper {

    @Mapping(target = "address", ignore = true)
    Firm toEntity(FirmDto firmDto);
    @Mapping(target = "address", ignore = true)
    FirmDto toDto(Firm firm);
}