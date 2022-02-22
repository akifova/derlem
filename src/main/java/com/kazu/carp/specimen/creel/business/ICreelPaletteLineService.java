package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.domain.CreelPaletteLine;
import com.kazu.carp.specimen.creel.dto.CreelPaletteLineDto;

import java.util.List;

/**
 * @author akifova
 * 12.05.2021
 */
public interface ICreelPaletteLineService {
    CreelPaletteLineDto create(CreelPaletteLineDto creelPaletteLineDto);

    void update(CreelPaletteLineDto creelPaletteLineDto);

    List<CreelPaletteLineDto> findByPaletteId(String paletteId);

    CreelPaletteLineDto findOne(String id);

    void delete(String id);

    CreelPaletteLine getById(String id);
}
