package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Collection;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.CollectionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 06.02.2021
 */
public interface ICollectionDao extends JpaRepository<Collection, String>, JpaSpecificationExecutor<Collection> {
    CollectionDto findFirstByCode(String code);

    CollectionDto findFirstByDefinition(String definition);
}
