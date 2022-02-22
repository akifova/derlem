package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.yarn.definition.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 01.05.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface IYarnMapper {

    @Mapping(target = "yarnGroups", ignore = true)
    YarnType toEntity(YarnTypeDto yarnTypeDto);
    @Mapping(target = "yarnGroups", ignore = true)
    YarnTypeDto toDto(YarnType yarnType);

    @Mappings({
            @Mapping(target = "yarnType", ignore = true),
            @Mapping(target = "sampleYarns", ignore = true),
            @Mapping(target = "yarns", ignore = true),
            @Mapping(target = "properties", ignore = true)
    })
    YarnGroup toEntity(YarnGroupDto yarnGroupDto);
    @Mappings({
            @Mapping(target = "yarnType", ignore = true),
            @Mapping(target = "sampleYarns", ignore = true),
            @Mapping(target = "yarns", ignore = true),
            @Mapping(target = "properties", ignore = true)
    })
    YarnGroupDto toDto(YarnGroup yarnGroup);

    @Mappings({
            @Mapping(target = "yarnGroupToProperties", ignore = true),
            @Mapping(target = "yarnPropertyLines", ignore = true)
    })
    YarnProperty toEntity(YarnPropertyDto yarnPropertyDto);
    @Mappings({
            @Mapping(target = "yarnGroupToProperties", ignore = true),
            @Mapping(target = "yarnPropertyLines", ignore = true)
    })
    YarnPropertyDto toDto(YarnProperty yarnProperty);

    @Mappings({
            @Mapping(target = "yarnProperty", ignore = true),
            @Mapping(target = "yarnToPropertyLines", ignore = true)
    })
    YarnPropertyLine toEntity(YarnPropertyLineDto yarnPropertyLineDto);
    @Mappings({
            @Mapping(target = "yarnProperty", ignore = true),
            @Mapping(target = "yarnToPropertyLines", ignore = true)
    })
    YarnPropertyLineDto toDto(YarnPropertyLine yarnPropertyLine);

    YarnGroupToProperty toEntity(YarnGroupToPropertyDto yarnGroupToPropertyDto);
    YarnGroupToPropertyDto toDto(YarnGroupToProperty yarnGroupToProperty);

    YarnToPropertyLine toEntity(YarnToPropertyLineDto yarnToPropertyLineDto);
    YarnToPropertyLineDto toDto(YarnToPropertyLine yarnToPropertyLine);

    @Mappings({
            @Mapping(target = "unitOfMeasure", ignore = true),
            @Mapping(target = "yarnGroup", ignore = true),
            @Mapping(target = "yarnToPropertyLines", ignore = true),
            @Mapping(target = "paletteLineToYarns", ignore = true),
            @Mapping(target = "creelPaletteLineToYarns", ignore = true),
            @Mapping(target = "bottomWoofYarn", ignore = true),
            @Mapping(target = "middleWoofYarn", ignore = true),
            @Mapping(target = "topWoofYarn", ignore = true),
            @Mapping(target = "weightUnit", ignore = true)
    })
    Yarn toEntity(YarnDto yarnDto);
    @Mappings({
            @Mapping(target = "unitOfMeasure", ignore = true),
            @Mapping(target = "yarnGroup", ignore = true),
            @Mapping(target = "yarnToPropertyLines", ignore = true),
            @Mapping(target = "paletteLineToYarns", ignore = true),
            @Mapping(target = "creelPaletteLineToYarns", ignore = true),
            @Mapping(target = "bottomWoofYarn", ignore = true),
            @Mapping(target = "middleWoofYarn", ignore = true),
            @Mapping(target = "topWoofYarn", ignore = true),
            @Mapping(target = "weightUnit", ignore = true)
    })
    YarnDto toDto(Yarn yarn);
}