package com.kazu.carp.common.firm.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.address.business.IAddressService;
import com.kazu.carp.common.address.domain.Address;
import com.kazu.carp.common.address.dto.AddressDto;
import com.kazu.carp.common.firm.domain.Firm;
import com.kazu.carp.common.firm.dto.FirmDto;
import com.kazu.carp.common.firm.dto.IFirmMapper;
import com.kazu.carp.common.firm.persistence.IFirmDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 29.04.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class FirmServiceImpl implements IFirmService {
    private static final Logger logger = LoggerFactory.getLogger(FirmServiceImpl.class);

    final IFirmDao dao;
    final IAddressService addressService;
    final IFirmMapper mapper;

    public FirmServiceImpl(IFirmDao dao, IAddressService addressService, IFirmMapper mapper) {
        this.dao = dao;
        this.addressService = addressService;
        this.mapper = mapper;
    }

    @Override
    public FirmDto create(FirmDto firmDto) {
        Firm firm = Firm.builder()
                .name(firmDto.getName())
                .tradeName(firmDto.getTradeName())
                .taxOffice(firmDto.getTaxOffice())
                .taxNumber(firmDto.getTaxNumber())
                .supplier(firmDto.isSupplier())
                .customer(firmDto.isCustomer())
                .outsourcer(firmDto.isOutsourcer())
                .isPersonCompany(firmDto.getIsPersonCompany())
                .address(addressService.getNewAddress(firmDto.getAddress()))
                .build();
        dao.save(firm);
        FirmDto dto = mapper.toDto(firm);
        dto.setAddress(addressService.getAddressWithDetailByEntity(firm.getAddress()));
        System.out.println(dto);
        return dto;
    }

    @Override
    public void update(FirmDto firmDto) {
        Firm firm = dao.getById(firmDto.getId());
        firm.setName(firmDto.getName());
        firm.setTradeName(firmDto.getTradeName());
        firm.setTaxOffice(firmDto.getTaxOffice());
        firm.setTaxNumber(firmDto.getTaxNumber());
        firm.setSupplier(firmDto.isSupplier());
        firm.setCustomer(firmDto.isCustomer());
        firm.setOutsourcer(firmDto.isOutsourcer());
        firm.setIsPersonCompany(firmDto.getIsPersonCompany());
        firm.setAddress(addressService.getAddressByAddressDto(firmDto.getAddress()));
        dao.save(firm);
    }

    @Override
    public List<FirmDto> findAll() {
        return dao.findAll().stream().map(m->{
            FirmDto dto = mapper.toDto(m);
            dto.setAddress(addressService.getAddressWithDetailByEntity(m.getAddress()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public FirmDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public Firm getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }

    @Override
    public List<FirmDto> search(String sp) {
        var spec = new SpecCreator<Firm>(sp).getSpec();
        return dao.findAll(spec).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void updateAddress(String firmId, AddressDto addressDto) {
        Firm firm = dao.getById(firmId);
        Address address = firm.getAddress();

        if(address == null) {
            address = addressService.getNewAddress(addressDto);
        } else {
            addressService.setAddressValuesByAdressDto(address, addressDto);
        }

        //address.setFirms(Arrays.asList(firm));
        firm.setAddress(address);
        dao.save(firm);
    }
}