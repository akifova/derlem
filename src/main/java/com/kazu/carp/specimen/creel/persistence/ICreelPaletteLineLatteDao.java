package com.kazu.carp.specimen.creel.persistence;

import com.kazu.carp.specimen.creel.domain.CreelPaletteLineToYarn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 12.05.2021
 */
public interface ICreelPaletteLineLatteDao extends JpaRepository<CreelPaletteLineToYarn, String> {
    List<CreelPaletteLineToYarn> findByCreelPaletteLineId(String lineId);
}
