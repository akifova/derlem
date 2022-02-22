package com.kazu.carp.specimen.creel.persistence;

import com.kazu.carp.specimen.creel.domain.CreelPalette;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 12.05.2021
 */
public interface ICreelPaletteDao extends JpaRepository<CreelPalette, String> {
    List<CreelPalette> findByCreelId(String creelId);
}
