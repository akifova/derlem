package com.kazu.carp.common.address.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.address.domain.County;
import com.kazu.carp.common.address.dto.CountyDto;
import com.kazu.carp.common.address.dto.IAddressMapper;
import com.kazu.carp.common.address.persistence.ICountyDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sarparda on 01.06.2017.
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class CountyServiceImpl implements ICountyService {

    final ICountyDao dao;
    final IAddressMapper mapper;

    public CountyServiceImpl(ICountyDao dao, IAddressMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public County getById(String countyId) {
        County county = dao.getById(countyId);
        return county;
    }

    @Override
    public List<CountyDto> search(String sp) {
        var spec = new SpecCreator<County>(sp).getSpec();
        var counties = dao.findAll(spec).stream().map(m-> {
            var dto = mapper.toDto(m);
            dto.setCity(mapper.toDto(m.getCity()));
            return dto;
        }).collect(Collectors.toList());
        return counties;
    }
}