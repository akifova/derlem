package com.kazu.carp.common.address.business;

import com.kazu.carp.common.address.domain.Region;
import com.kazu.carp.common.address.dto.IAddressMapper;
import com.kazu.carp.common.address.dto.RegionDto;
import com.kazu.carp.common.address.persistence.IRegionDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sarparda on 26.05.2017.
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class RegionServiceImpl implements IRegionService {

    final IRegionDao dao;
    final IAddressMapper mapper;

    public RegionServiceImpl(IRegionDao dao, IAddressMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public List<RegionDto> findAll() {
        List<Region> regions = dao.findAll();
        List<RegionDto> regionDtos = regions.stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
        return regionDtos;
    }

    @Override
    public Region findOne(String regionId) {
        Region region = dao.getById(regionId);
        return region;
    }
}