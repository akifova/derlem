package com.kazu.carp.common.brand.business;

import com.kazu.carp.common.brand.domain.Brand;
import com.kazu.carp.common.brand.dto.BrandDto;

import java.util.List;

/**
 * @author akifova
 * 06.02.2021
 */
public interface IBrandService {
    Brand getById(String id);

    BrandDto create(BrandDto brandDto);

    void update(BrandDto brandDto);

    List<BrandDto> findAll();

    BrandDto findOne(String id);

    void delete(String id);
}
