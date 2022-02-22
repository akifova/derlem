package com.kazu.carp.common.brand.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.common.brand.domain.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author akifova
 * 06.02.2021
 */
@Mapper(config = MapStructConfig.class)
public interface IBrandMapper {
    @Mapping(target = "collections", ignore = true)
    Brand toEntity(BrandDto brandDto);

    @Mapping(target = "collections", ignore = true)
    BrandDto toDto(Brand brand);
}
