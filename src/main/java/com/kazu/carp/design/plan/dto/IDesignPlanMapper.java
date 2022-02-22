package com.kazu.carp.design.plan.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.design.plan.domain.DesignPlanToUserFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 14.02.2021
 */
@Mapper(config = MapStructConfig.class)
public interface IDesignPlanMapper {
    @Mappings({
            @Mapping(target = "task", ignore = true),
            @Mapping(target = "owner", ignore = true),
            @Mapping(target = "designPlanToFiles", ignore = true),
            @Mapping(target = "creels", ignore = true)
    })
    DesignPlan toEntity(DesignPlanDto designPlanDto);
    @Mappings({
            @Mapping(target = "task", ignore = true),
            @Mapping(target = "owner", ignore = true),
            @Mapping(target = "designPlanToFiles", ignore = true),
            @Mapping(target = "creels", ignore = true)
    })
    DesignPlanDto toDto(DesignPlan designPlan);

    DesignPlanToUserFile toEntity(DesignPlanToUserFileDto designPlanToUserFileDto);
    DesignPlanToUserFileDto toDto(DesignPlanToUserFile designPlanToUserFile);
}