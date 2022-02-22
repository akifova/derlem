package com.kazu.carp.common.uom.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.common.uom.domain.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 29.04.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface IUOMMapper {

    @Mappings({
            @Mapping(target = "yarns", ignore = true)
    })
    UnitOfMeasure toEntity(UnitOfMeasureDto unitOfMeasureDto);
    @Mappings({
            @Mapping(target = "yarns", ignore = true)
    })
    UnitOfMeasureDto toDto(UnitOfMeasure unitOfMeasure);
}