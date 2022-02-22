package com.kazu.carp.design.color.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.design.color.domain.Color;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 08.02.2021
 */
@Mapper(config = MapStructConfig.class)
public interface IDesignMapper {

    @Mappings({
            @Mapping(target = "lines", ignore = true),
            @Mapping(target = "creelPaletteLines", ignore = true)
    })
    Color toEntity(ColorDto color);
    @Mappings({
            @Mapping(target = "lines", ignore = true),
            @Mapping(target = "creelPaletteLines", ignore = true)
    })
    ColorDto toDto(Color color);
}