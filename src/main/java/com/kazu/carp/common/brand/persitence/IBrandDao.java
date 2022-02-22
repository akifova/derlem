package com.kazu.carp.common.brand.persitence;

import com.kazu.carp.common.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author akifova
 * 06.02.2021
 */
public interface IBrandDao extends JpaRepository<Brand, String> {
}
