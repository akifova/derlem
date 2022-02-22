package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 11.02.2021
 */
public interface IShapeDao extends JpaRepository<Shape, String>, JpaSpecificationExecutor<Shape> {
    Shape findFirstByCode(String code);
}
