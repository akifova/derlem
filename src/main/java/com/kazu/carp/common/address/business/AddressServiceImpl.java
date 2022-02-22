package com.kazu.carp.common.address.business;

import com.kazu.carp.common.address.domain.Address;
import com.kazu.carp.common.address.domain.County;
import com.kazu.carp.common.address.domain.Mail;
import com.kazu.carp.common.address.domain.Phone;
import com.kazu.carp.common.address.dto.*;
import com.kazu.carp.common.address.persistence.IAddressDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class AddressServiceImpl implements IAddressService{

    final ICountyService countyService;
    final IAddressDao addressDao;
    final IAddressMapper mapper;

    public AddressServiceImpl(ICountyService countyService, IAddressDao addressDao, IAddressMapper mapper) {
        this.countyService = countyService;
        this.addressDao = addressDao;
        this.mapper = mapper;
    }

    @Override
    public Address getNewAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setName(addressDto.getName() == null ? "Ana Adres" : addressDto.getName());
        address.setAddress(addressDto.getAddress());
        address.setWebUrl(addressDto.getWebUrl() == null ? "http://www" : addressDto.getWebUrl());

        if (addressDto.getCounty().getId() != null){
            County county = countyService.getById(addressDto.getCounty().getId());
            address.setCounty(county);
        }

        List<Mail> mails = new ArrayList<>();
        List<Phone> phones = new ArrayList<>();

        if (addressDto.getMails() != null){
            addressDto.getMails().stream().forEach(m->{
                Mail mail = new Mail();
                mail.setAddress(address);
                mail.setMailAddress(m.getMailAddress());
                mail.setMailType(m.getMailType() == null ? MailType.work : m.getMailType());
                mails.add(mail);
            });
        }

        if (addressDto.getPhones() != null){
            addressDto.getPhones().stream().forEach(p->{
                Phone phone = new Phone();
                phone.setAddress(address);
                phone.setPhoneNumber(p.getPhoneNumber());
                phone.setPhoneType(p.getPhoneType() == null ? PhoneType.mobile : p.getPhoneType());
                phones.add(phone);
            });
        }

        address.setMails(mails);
        address.setPhones(phones);

        return address;
    }

    @Override
    public Address getAddressById(String addressId) {
        Address address = addressDao.getById(addressId);
        return address;
    }

    @Override
    public Address getAddressByAddressDto(AddressDto addressDto) {
        County county = countyService.getById(addressDto.getCounty().getId());
        Address address;
        Mail mail;
        Phone phone;
        if(addressDto.getId() == null) {
            address = new Address();
            mail = new Mail();
            phone = new Phone();

            List<Mail> mails = new ArrayList<>();
            mails.add(mail);
            address.setMails(mails);

            List<Phone> phones = new ArrayList<>();
            phones.add(phone);
            address.setPhones(phones);
        } else {
            address = getAddressById(addressDto.getId());
            phone = address.getPhones().get(0);
            mail = address.getMails().get(0);
        }

        address.setAddress(addressDto.getAddress());
        address.setCounty(county);
        address.setName(StringUtils.isEmpty(address.getName()) ? "Ana Adres" : address.getName());
        address.setWebUrl(StringUtils.isEmpty(address.getWebUrl()) ? "http://www." : address.getWebUrl());

        mail.setAddress(address);
        mail.setMailAddress(addressDto.getMails().get(0).getMailAddress());
        mail.setMailType(mail.getMailType() == null ? MailType.work : mail.getMailType());

        phone.setAddress(address);
        phone.setPhoneNumber(addressDto.getPhones().get(0).getPhoneNumber());
        phone.setPhoneType(phone.getPhoneType() == null ? PhoneType.office : phone.getPhoneType());

        return address;
    }

    @Override
    public AddressDto getAddressWithDetailById(String addressId) {
        Address address = getAddressById(addressId);
        AddressDto dto = mapper.toDto(address);
        List<MailDto> mails = address.getMails().stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
        List<PhoneDto> phones = address.getPhones().stream().map(p->mapper.toDto(p)).collect(Collectors.toList());
        dto.setMails(mails);
        dto.setPhones(phones);

        County county = countyService.getById(address.getCounty().getId());
        CountyDto countyDto = mapper.toDto(county);
        countyDto.setCity(mapper.toDto(county.getCity()));
        dto.setCounty(countyDto);
        return dto;
    }

    @Override
    public void setAddressValuesByAdressDto(Address address, AddressDto addressDto) {
        address.setAddress(addressDto.getAddress());
        address.setCounty(addressDto.getCounty().getId() == null ? null : countyService.getById(addressDto.getCounty().getId()));
        address.setName(StringUtils.isEmpty(address.getName()) ? "Ana Adres" : addressDto.getName());
        address.setWebUrl(StringUtils.isEmpty(address.getWebUrl()) ? "http://www." : addressDto.getWebUrl());

        if (address.getMails() != null){
            Mail mail = address.getMails().get(0);
            mail.setAddress(address);
            mail.setMailAddress(addressDto.getMails().get(0).getMailAddress());
            mail.setMailType(addressDto.getMails().get(0).getMailType() == null ? MailType.work : addressDto.getMails().get(0).getMailType());
        }

        if (address.getPhones() != null){
            Phone phone = address.getPhones().get(0);
            phone.setAddress(address);
            phone.setPhoneNumber(addressDto.getPhones().get(0).getPhoneNumber());
            phone.setPhoneType(addressDto.getPhones().get(0).getPhoneType() == null ? PhoneType.office : addressDto.getPhones().get(0).getPhoneType());
        }
    }

    @Override
    public Address getAddressByCountyAndAddress(String countyId, String addressStr) {
        Address address = addressDao.findByCountyIdAndAddress(countyId, addressStr);
        return address;
    }

    @Override
    public AddressDto getAddressWithDetailByEntity(Address address) {
        if(address == null) {
            return null;
        }
        AddressDto dto = mapper.toDto(address);
        List<MailDto> mails = address.getMails().stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
        List<PhoneDto> phones = address.getPhones().stream().map(p->mapper.toDto(p)).collect(Collectors.toList());
        dto.setMails(mails);
        dto.setPhones(phones);

        County county = countyService.getById(address.getCounty().getId());
        CountyDto countyDto = mapper.toDto(county);
        countyDto.setCity(mapper.toDto(county.getCity()));
        dto.setCounty(countyDto);
        return dto;
    }

    @Override
    public void update(AddressDto addressDto) {
        Address address = addressDao.getById(addressDto.getId());
        setAddressValuesByAdressDto(address, addressDto);
        addressDao.save(address);
    }

    @Override
    public AddressDto findOne(String id) {
        return getAddressWithDetailById(id);
    }

    @Override
    public List<AddressDto> findByFirmId(String firmId) {
        List<Address> addresses = addressDao.findByFirmsId(firmId);
        return addresses.stream().map(m-> getAddressWithDetailByEntity(m)).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        addressDao.deleteById(id);
    }
}
