package com.kazu.carp.common.brand.business;

import com.kazu.carp.common.brand.domain.Brand;
import com.kazu.carp.common.brand.dto.BrandDto;
import com.kazu.carp.common.brand.dto.IBrandMapper;
import com.kazu.carp.common.brand.persitence.IBrandDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 06.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class BrandServiceImpl implements IBrandService {
    private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    final IBrandDao dao;
    final IBrandMapper mapper;

    public BrandServiceImpl(IBrandDao dao, IBrandMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Brand getById(String id) {
        return dao.getById(id);
    }

    @Override
    public BrandDto create(BrandDto brandDto) {
        Brand brand = Brand.builder()
                .name(brandDto.getName())
                .code(brandDto.getCode())
                .build();
        dao.save(brand);
        return mapper.toDto(brand);
    }

    @Override
    public void update(BrandDto brandDto) {
        Brand brand = dao.getById(brandDto.getId());
        brand.setName(brandDto.getName());
        brand.setCode(brandDto.getCode());
        dao.save(brand);
    }

    @Override
    public List<BrandDto> findAll() {
        return dao.findAll().stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public BrandDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }
}