package com.kazu.carp.specimen.creel.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.specimen.creel.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 05.05.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface ICreelMapper {

    @Mappings({
            @Mapping(target = "bottomWoofYarn", ignore = true),
            @Mapping(target = "middleWoofYarn", ignore = true),
            @Mapping(target = "topWoofYarn", ignore = true),
            @Mapping(target = "creel", ignore = true)
    })
    CreelQuality toEntity(CreelQualityDto creelQualityDto);
    @Mappings({
            @Mapping(target = "bottomWoofYarn", ignore = true),
            @Mapping(target = "middleWoofYarn", ignore = true),
            @Mapping(target = "topWoofYarn", ignore = true),
            @Mapping(target = "creel", ignore = true)
    })
    CreelQualityDto toDto(CreelQuality creelQuality);

    @Mappings({
            @Mapping(target = "creelPaletteLines", ignore = true),
            @Mapping(target = "creel", ignore = true)
    })
    CreelPalette toEntity(CreelPaletteDto creelPaletteDto);
    @Mappings({
            @Mapping(target = "creelPaletteLines", ignore = true),
            @Mapping(target = "creel", ignore = true)
    })
    CreelPaletteDto toDto(CreelPalette creelPalette);

    @Mappings({
            @Mapping(target = "lineToYarns", ignore = true),
            @Mapping(target = "creelPalette", ignore = true),
            @Mapping(target = "color", ignore = true)
    })
    CreelPaletteLine toEntity(CreelPaletteLineDto creelPaletteLineDto);
    @Mappings({
            @Mapping(target = "lineToYarns", ignore = true),
            @Mapping(target = "creelPalette", ignore = true),
            @Mapping(target = "color", ignore = true)
    })
    CreelPaletteLineDto toDto(CreelPaletteLine creelPaletteLine);

    @Mappings({
            @Mapping(target = "creelPaletteLine", ignore = true),
            @Mapping(target = "yarn", ignore = true),
            @Mapping(target = "sampleYarn", ignore = true)
    })
    CreelPaletteLineToYarn toEntity(CreelPaletteLineToYarnDto creelPaletteLineToYarnDto);
    @Mappings({
            @Mapping(target = "creelPaletteLine", ignore = true),
            @Mapping(target = "yarn", ignore = true),
            @Mapping(target = "sampleYarn", ignore = true)
    })
    CreelPaletteLineToYarnDto toDto(CreelPaletteLineToYarn creelPaletteLineToYarn);

    @Mappings({
            @Mapping(target = "creelQuality", ignore = true),
            @Mapping(target = "creelPalette", ignore = true),
            @Mapping(target = "explanations", ignore = true),
            @Mapping(target = "designPlan", ignore = true),
            @Mapping(target = "archives", ignore = true)
    })
    Creel toEntity(CreelDto creelDto);
    @Mappings({
            @Mapping(target = "creelQuality", ignore = true),
            @Mapping(target = "creelPalette", ignore = true),
            @Mapping(target = "explanations", ignore = true),
            @Mapping(target = "designPlan", ignore = true),
            @Mapping(target = "archives", ignore = true)
    })
    CreelDto toDto(Creel creel);

    @Mappings({
            @Mapping(target = "creel", ignore = true),
            @Mapping(target = "explanation", ignore = true)
    })
    CreelToExplanation toEntity(CreelToExplanationDto creelToExplanationDto);
    @Mappings({
            @Mapping(target = "creel", ignore = true),
            @Mapping(target = "explanation", ignore = true)
    })
    CreelToExplanationDto toDto(CreelToExplanation creelToExplanation);
}