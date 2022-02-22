package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLine;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineDto;

import java.util.List;

/**
 * @author akifova
 * 07.02.2021
 */
public interface IPaletteLineService {
    PaletteLineDto create(PaletteLineDto paletteLineDto);

    void update(PaletteLineDto paletteLineDto);

    List<PaletteLineDto> findAll();

    PaletteLineDto findOne(String id);

    PaletteLine getById(String id);

    void delete(String id);

    List<PaletteLineDto> search(String sp);

    List<PaletteLineDto> findByPaletteId(String paletteId);
}
