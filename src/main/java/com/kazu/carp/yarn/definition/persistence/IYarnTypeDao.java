package com.kazu.carp.yarn.definition.persistence;

import com.kazu.carp.yarn.definition.domain.YarnType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author akifova
 * 01.05.2021
 */
public interface IYarnTypeDao extends JpaRepository<YarnType, String> {
}
