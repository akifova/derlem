package com.kazu.carp.common.uom.persistence;

import com.kazu.carp.common.uom.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author akifova
 * 29.04.2021
 */
public interface IUOMDao extends JpaRepository<UnitOfMeasure, String>, JpaSpecificationExecutor<UnitOfMeasure> {
    List<UnitOfMeasure> findByUnitLike(String s);
}
