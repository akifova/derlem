package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Collection;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.CollectionDto;

import java.util.List;

/**
 * @author akifova
 * 06.02.2021
 */
public interface ICollectionService {
    CollectionDto create(CollectionDto collectionDto);

    void update(CollectionDto collectionDto);

    List<CollectionDto> findAll();

    CollectionDto findOne(String id);

    CollectionDto findByCode(String code);

    CollectionDto findByDefinition(String definition);

    void remove(String id);

    List<CollectionDto> search(String sp);

    Collection getById(String id);
}
