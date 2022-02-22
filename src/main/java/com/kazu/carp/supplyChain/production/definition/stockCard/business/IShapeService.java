package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Shape;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.ShapeDto;

import java.util.List;

/**
 * @author akifova
 * 11.02.2021
 */
public interface IShapeService {
    ShapeDto create(ShapeDto shapeDto);

    void update(ShapeDto shapeDto);

    List<ShapeDto> findAll();

    ShapeDto findOne(String id);

    ShapeDto findFirstByCode(String code);

    void delete(String id);

    List<ShapeDto> search(String sp);

    Shape getById(String id);
}
