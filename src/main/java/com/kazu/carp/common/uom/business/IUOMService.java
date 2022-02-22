package com.kazu.carp.common.uom.business;

import com.kazu.carp.common.uom.domain.UnitOfMeasure;
import com.kazu.carp.common.uom.dto.UnitOfMeasureDto;

import java.util.List;

/**
 * @author akifova
 * 29.04.2021
 */
public interface IUOMService {
    UnitOfMeasureDto create(UnitOfMeasureDto unitOfMeasureDto);

    void update(UnitOfMeasureDto unitOfMeasureDto);

    List<UnitOfMeasureDto> findAll();

    UnitOfMeasureDto findOne(String id);

    void delete(String id);

    List<UnitOfMeasureDto> findByNameLike(String searchTerm);

    UnitOfMeasure getById(String id);

    List<UnitOfMeasureDto> search(String sp);
}
