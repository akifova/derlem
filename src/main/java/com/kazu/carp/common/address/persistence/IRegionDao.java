package com.kazu.carp.common.address.persistence;

import com.kazu.carp.common.address.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sarparda on 26.05.2017.
 */
public interface IRegionDao extends JpaRepository<Region, String> {
}
