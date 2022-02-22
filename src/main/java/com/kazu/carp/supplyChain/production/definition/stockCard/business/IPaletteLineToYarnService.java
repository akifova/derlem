package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLineToYarn;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineToYarnDto;

import java.util.List;

/**
 * @author akifova
 * 08.02.2021
 */
public interface IPaletteLineToYarnService {
    PaletteLineToYarnDto create(PaletteLineToYarnDto latteDto);

    void update(PaletteLineToYarnDto latteDto);

    List<PaletteLineToYarnDto> findAll();

    PaletteLineToYarnDto findOne(String id);

    void delete(String id);

    List<PaletteLineToYarnDto> search(String sp);

    PaletteLineToYarn getById(String id);
}
