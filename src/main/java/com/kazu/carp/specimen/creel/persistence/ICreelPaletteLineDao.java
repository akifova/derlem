package com.kazu.carp.specimen.creel.persistence;

import com.kazu.carp.specimen.creel.domain.CreelPaletteLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 12.05.2021
 */
public interface ICreelPaletteLineDao extends JpaRepository<CreelPaletteLine, String> {
    List<CreelPaletteLine> findByCreelPaletteId(String paletteId);
}
