package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.domain.CreelPalette;
import com.kazu.carp.specimen.creel.dto.CreelPaletteDto;

import java.util.List;

/**
 * @author akifova
 * 12.05.2021
 */
public interface ICreelPaletteService {
    CreelPalette getById(String id);

    CreelPaletteDto findOne(String id);

    List<CreelPaletteDto> findByCreelId(String creelId);
}
