package com.kazu.carp.common.firm.business;

import com.kazu.carp.common.address.dto.AddressDto;
import com.kazu.carp.common.firm.domain.Firm;
import com.kazu.carp.common.firm.dto.FirmDto;

import java.util.List;

/**
 * @author akifova
 * 29.04.2021
 */
public interface IFirmService {
    FirmDto create(FirmDto firmDto);

    void update(FirmDto firmDto);

    List<FirmDto> findAll();

    FirmDto findOne(String id);

    void delete(String id);

    Firm getById(String id);

    List<FirmDto> search(String sp);

    void updateAddress(String firmId, AddressDto addressDto);
}
