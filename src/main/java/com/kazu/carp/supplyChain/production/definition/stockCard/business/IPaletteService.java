package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Palette;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteFindSimilarDto;
import com.kazu.carp.yarn.definition.dto.YarnDto;

import java.util.List;

/**
 * @author akifova
 * 07.02.2021
 */
public interface IPaletteService {
    PaletteDto create(PaletteDto paletteDto);

    void update(PaletteDto paletteDto);

    List<PaletteDto> findAll();

    PaletteDto findOne(String id);

    PaletteDto findFirstByCode(String code);

    void delete(String id);

    Palette getById(String id);

    List<PaletteDto> search(String sp);

    List<YarnDto> getYarns(String id);

    List<PaletteFindSimilarDto> findSimilar(List<String> colorCodes);
}
