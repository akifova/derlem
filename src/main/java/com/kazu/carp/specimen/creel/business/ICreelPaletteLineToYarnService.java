package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.dto.CreelPaletteLineToYarnDto;

import java.util.List;

/**
 * @author akifova
 * 12.05.2021
 */
public interface ICreelPaletteLineToYarnService {
    CreelPaletteLineToYarnDto create(CreelPaletteLineToYarnDto creelPaletteLineToYarnDto);

    CreelPaletteLineToYarnDto update(CreelPaletteLineToYarnDto creelPaletteLineToYarnDto);

    List<CreelPaletteLineToYarnDto> findByPaletteLineId(String lineId);

    CreelPaletteLineToYarnDto findOne(String id);

    void delete(String id);
}
