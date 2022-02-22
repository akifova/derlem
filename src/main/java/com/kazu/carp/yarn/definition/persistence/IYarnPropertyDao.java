package com.kazu.carp.yarn.definition.persistence;

import com.kazu.carp.yarn.definition.domain.YarnProperty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author akifova
 * 05.05.2021
 */
public interface IYarnPropertyDao extends JpaRepository<YarnProperty, String> {
}
