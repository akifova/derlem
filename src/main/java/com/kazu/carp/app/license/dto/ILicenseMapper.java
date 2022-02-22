package com.kazu.carp.app.license.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.app.license.domain.License;
import org.mapstruct.Mapper;

/**
 * @author akifova
 * 06.10.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface ILicenseMapper {

    License toEntity(LicenseDto licenseDto);

    LicenseDto toDto(License license);
}