package com.kazu.carp.design.archive.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.design.archive.domain.Archive;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 01.07.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface IArchiveMapper {

    @Mappings({
            @Mapping(target = "creel", ignore = true),
            @Mapping(target = "collection", ignore = true),
            @Mapping(target = "quality", ignore = true),
            @Mapping(target = "palette", ignore = true),
            @Mapping(target = "bordure", ignore = true),
            @Mapping(target = "floor", ignore = true),
            @Mapping(target = "design", ignore = true),
            @Mapping(target = "size", ignore = true),
            @Mapping(target = "shape", ignore = true),
            @Mapping(target = "edge", ignore = true),
            @Mapping(target = "overlockYarn", ignore = true),
            @Mapping(target = "sideEdge", ignore = true),
            @Mapping(target = "customer", ignore = true),
            @Mapping(target = "designPlan", ignore = true),
            @Mapping(target = "designer", ignore = true),
            @Mapping(target = "archiveToUserFiles", ignore = true),
            @Mapping(target = "pixelRatios", ignore = true)
    })
    Archive toEntity(ArchiveDto archiveDto);

    @Mappings({
            @Mapping(target = "creel", ignore = true),
            @Mapping(target = "collection", ignore = true),
            @Mapping(target = "quality", ignore = true),
            @Mapping(target = "palette", ignore = true),
            @Mapping(target = "bordure", ignore = true),
            @Mapping(target = "floor", ignore = true),
            @Mapping(target = "design", ignore = true),
            @Mapping(target = "size", ignore = true),
            @Mapping(target = "shape", ignore = true),
            @Mapping(target = "edge", ignore = true),
            @Mapping(target = "overlockYarn", ignore = true),
            @Mapping(target = "sideEdge", ignore = true),
            @Mapping(target = "customer", ignore = true),
            @Mapping(target = "designPlan", ignore = true),
            @Mapping(target = "designer", ignore = true),
            @Mapping(target = "archiveToUserFiles", ignore = true),
            @Mapping(target = "pixelRatios", ignore = true)
    })
    ArchiveDto toDto(Archive archive);
}