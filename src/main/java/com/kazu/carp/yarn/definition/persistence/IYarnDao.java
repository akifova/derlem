package com.kazu.carp.yarn.definition.persistence;

import com.kazu.carp.yarn.definition.domain.Yarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 05.05.2021
 */
public interface IYarnDao extends JpaRepository<Yarn, String>, JpaSpecificationExecutor<Yarn> {
}
