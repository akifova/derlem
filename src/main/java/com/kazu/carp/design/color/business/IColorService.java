package com.kazu.carp.design.color.business;

import com.kazu.carp.design.color.domain.Color;
import com.kazu.carp.design.color.dto.ColorDto;

/**
 * @author akifova
 * 08.02.2021
 */
public interface IColorService {
    Color findByRgbRedAndRgbGreenAndRgbBlue(Integer rgbRed, Integer rgbGreen, Integer rgbBlue);

    Color createNewFromDto(ColorDto color);

    Color getFromDto(ColorDto color);
}
