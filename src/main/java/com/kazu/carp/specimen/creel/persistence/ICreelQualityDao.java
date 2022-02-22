package com.kazu.carp.specimen.creel.persistence;

import com.kazu.carp.specimen.creel.domain.CreelQuality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 09.05.2021
 */
public interface ICreelQualityDao extends JpaRepository<CreelQuality, String> {
    List<CreelQuality> findByCreelId(String creelId);
}
