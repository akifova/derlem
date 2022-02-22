package com.kazu.carp.common.address.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.common.address.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 29.04.2021
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface IAddressMapper {

    @Mappings({
            @Mapping(target = "mails", ignore = true),
            @Mapping(target = "phones", ignore = true),
            @Mapping(target = "county", ignore = true),
            @Mapping(target = "firms", ignore = true)
    })
    Address toEntity(AddressDto addressDto);
    @Mappings({
            @Mapping(target = "mails", ignore = true),
            @Mapping(target = "phones", ignore = true),
            @Mapping(target = "county", ignore = true),
            @Mapping(target = "firms", ignore = true)
    })
    AddressDto toDto(Address address);

    @Mappings({
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "region", ignore = true),
            @Mapping(target = "counties", ignore = true)
    })
    City toEntity(CityDto addressDto);
    @Mappings({
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "region", ignore = true),
            @Mapping(target = "counties", ignore = true)
    })
    CityDto toDto(City address);

    @Mappings({
            @Mapping(target = "city", ignore = true),
            @Mapping(target = "addresses", ignore = true)
    })
    County toEntity(CountyDto addressDto);
    @Mappings({
            @Mapping(target = "city", ignore = true),
            @Mapping(target = "addresses", ignore = true)
    })
    CountyDto toDto(County address);

    @Mappings({
            @Mapping(target = "regions", ignore = true),
            @Mapping(target = "cities", ignore = true)
    })
    Country toEntity(CountryDto addressDto);
    @Mappings({
            @Mapping(target = "regions", ignore = true),
            @Mapping(target = "cities", ignore = true)
    })
    CountryDto toDto(Country address);

    @Mappings({
            @Mapping(target = "address", ignore = true)
    })
    Mail toEntity(MailDto addressDto);
    @Mappings({
            @Mapping(target = "address", ignore = true)
    })
    MailDto toDto(Mail address);

    @Mappings({
            @Mapping(target = "address", ignore = true)
    })
    Phone toEntity(PhoneDto phoneDto);
    @Mappings({
            @Mapping(target = "address", ignore = true)
    })
    PhoneDto toDto(Phone phoneDto);

    @Mappings({
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "cities", ignore = true)
    })
    Region toEntity(RegionDto phoneDto);
    @Mappings({
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "cities", ignore = true)
    })
    RegionDto toDto(Region phoneDto);
}