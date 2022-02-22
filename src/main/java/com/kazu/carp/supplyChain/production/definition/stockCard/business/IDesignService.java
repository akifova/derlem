package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Design;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.DesignDto;

import java.util.List;

/**
 * @author akifova
 * 07.02.2021
 */
public interface IDesignService {
    DesignDto create(DesignDto designDto);

    void update(DesignDto designDto);

    List<DesignDto> findAll();

    DesignDto findOne(String id);

    DesignDto findFirstByCode(String code);

    void delete(String id);

    List<DesignDto> search(String sp);

    Design getById(String id);
}
