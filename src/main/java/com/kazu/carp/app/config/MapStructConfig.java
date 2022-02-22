package com.kazu.carp.app.config;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.jpa.BaseEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * @author akifova
 * @since 24.01.2021
 */
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG,
        componentModel = "spring"
)
public interface MapStructConfig {
    @InheritInverseConfiguration
    BaseEntity anyToEntity(BaseDto baseDto);
    @InheritInverseConfiguration
    BaseDto anyToDto(BaseEntity baseEntity);
}
