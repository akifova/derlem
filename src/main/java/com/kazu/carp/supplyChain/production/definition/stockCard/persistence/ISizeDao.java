package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 10.02.2021
 */
public interface ISizeDao extends JpaRepository<Size, String>, JpaSpecificationExecutor<Size> {
}
