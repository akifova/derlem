package com.kazu.carp.yarn.definition.persistence;

import com.kazu.carp.yarn.definition.domain.YarnGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 01.05.2021
 */
public interface IYarnGroupDao extends JpaRepository<YarnGroup, String>, JpaSpecificationExecutor<YarnGroup> {
}
