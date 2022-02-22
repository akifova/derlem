package com.kazu.carp.common.address.business;

import com.kazu.carp.common.address.domain.Address;
import com.kazu.carp.common.address.dto.AddressDto;

import java.util.List;

public interface IAddressService {
    Address getNewAddress(AddressDto addressDto);
    Address getAddressById(String addressId);

    Address getAddressByAddressDto(AddressDto addressDto);

    AddressDto getAddressWithDetailById(String addressId);

    void setAddressValuesByAdressDto(Address address, AddressDto addressDto);

    Address getAddressByCountyAndAddress(String countyId, String addressStr);

    AddressDto getAddressWithDetailByEntity(Address address);

    void update(AddressDto addressDto);

    AddressDto findOne(String id);

    List<AddressDto> findByFirmId(String firmId);

    void delete(String id);
}
