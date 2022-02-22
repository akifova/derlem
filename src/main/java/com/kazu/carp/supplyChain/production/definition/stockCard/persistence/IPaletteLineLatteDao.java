package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLineToYarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 08.02.2021
 */
public interface IPaletteLineLatteDao extends JpaRepository<PaletteLineToYarn, String>, JpaSpecificationExecutor<PaletteLineToYarn> {
}
