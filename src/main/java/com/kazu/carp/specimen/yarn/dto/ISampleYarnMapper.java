package com.kazu.carp.specimen.yarn.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.specimen.yarn.domain.SampleYarn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 30.04.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface ISampleYarnMapper {

    @Mappings({
            @Mapping(target = "weightUnit", ignore = true),
            @Mapping(target = "mainSampleYarn", ignore = true),
            @Mapping(target = "sampleYarnVersions", ignore = true),
            @Mapping(target = "supplier", ignore = true),
            @Mapping(target = "yarnGroup", ignore = true)
    })
    SampleYarn toEntity(SampleYarnDto sampleYarnDto);
    @Mappings({
            @Mapping(target = "weightUnit", ignore = true),
            @Mapping(target = "mainSampleYarn", ignore = true),
            @Mapping(target = "sampleYarnVersions", ignore = true),
            @Mapping(target = "supplier", ignore = true),
            @Mapping(target = "yarnGroup", ignore = true)
    })
    SampleYarnDto toDto(SampleYarn sampleYarn);
}