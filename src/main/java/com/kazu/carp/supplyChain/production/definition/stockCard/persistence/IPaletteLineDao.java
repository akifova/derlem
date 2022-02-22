package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author akifova
 * 07.02.2021
 */
public interface IPaletteLineDao extends JpaRepository<PaletteLine, String>, JpaSpecificationExecutor<PaletteLine> {
    List<PaletteLine> findByPaletteId(String paletteId);
}
