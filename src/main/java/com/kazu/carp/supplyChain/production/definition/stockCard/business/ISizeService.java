package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Size;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.SizeDto;

import java.util.List;

/**
 * @author akifova
 * 10.02.2021
 */
public interface ISizeService {
    SizeDto create(SizeDto sizeDto);

    void update(SizeDto sizeDto);

    List<SizeDto> findAll();

    SizeDto findOne(String id);

    void delete(String id);

    List<SizeDto> search(String sp);

    Size getById(String id);
}
