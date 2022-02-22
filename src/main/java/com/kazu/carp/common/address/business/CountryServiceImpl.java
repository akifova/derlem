package com.kazu.carp.common.address.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.address.domain.Country;
import com.kazu.carp.common.address.dto.CountryDto;
import com.kazu.carp.common.address.dto.IAddressMapper;
import com.kazu.carp.common.address.persistence.ICountryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class CountryServiceImpl implements ICountryService {
    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    final ICountryDao dao;

    final IAddressMapper mapper;

    public CountryServiceImpl(ICountryDao dao, IAddressMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public CountryDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public Country getById(String id) {
        return dao.getById(id);
    }

    @Override
    public List<CountryDto> search(String sp) {
        var spec = new SpecCreator<Country>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }
}