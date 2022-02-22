package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Design;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.DesignDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 07.02.2021
 */
public interface IDesignDao extends JpaRepository<Design, String>, JpaSpecificationExecutor<Design> {
    DesignDto findFirstByCode(String code);
}
