package com.kazu.carp.specimen.yarn.persistence;

import com.kazu.carp.specimen.yarn.domain.SampleYarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author akifova
 * 30.04.2021
 */
public interface ISampleYarnJpaDao extends JpaRepository<SampleYarn, String>, JpaSpecificationExecutor<SampleYarn> {
    List<SampleYarn> findByMainSampleYarnId(String id);

    List<SampleYarn> findByActive(boolean isActive);
}
