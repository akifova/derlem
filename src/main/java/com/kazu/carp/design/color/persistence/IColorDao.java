package com.kazu.carp.design.color.persistence;

import com.kazu.carp.design.color.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author akifova
 * 08.02.2021
 */
public interface IColorDao extends JpaRepository<Color, String> {
    Color findByRgbRedAndRgbGreenAndRgbBlue(Integer rgbRed, Integer rgbGreen, Integer rgbBlue);
}
