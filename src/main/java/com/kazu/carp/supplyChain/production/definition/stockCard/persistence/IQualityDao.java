package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 06.02.2021
 */
public interface IQualityDao extends JpaRepository<Quality, String>, JpaSpecificationExecutor<Quality> {
    Quality findFirstByCode(String code);
}
