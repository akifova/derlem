package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 10.02.2021
 */
public interface IEdgeDao extends JpaRepository<Edge, String>, JpaSpecificationExecutor<Edge> {
    Edge findFirstByCode(String code);
}
