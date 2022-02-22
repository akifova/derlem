package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Edge;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.EdgeDto;

import java.util.List;

/**
 * @author akifova
 * 10.02.2021
 */
public interface IEdgeService {
    EdgeDto create(EdgeDto edgeDto);

    void update(EdgeDto edgeDto);

    List<EdgeDto> findAll();

    EdgeDto findOne(String id);

    EdgeDto findFirstByCode(String code);

    List<EdgeDto> search(String sp);

    Edge getById(String id);

    void delete(String id);
}
