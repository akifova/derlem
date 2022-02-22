package com.kazu.carp.yarn.definition.persistence;

import com.kazu.carp.yarn.definition.domain.YarnPropertyLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 05.05.2021
 */
public interface IYarnPropertyLineDao extends JpaRepository<YarnPropertyLine, String> {
    List<YarnPropertyLine> findByYarnPropertyId(String propertyId);
}
