package com.kazu.carp.yarn.definition.persistence;

import com.kazu.carp.yarn.definition.domain.YarnToPropertyLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 06.05.2021
 */
public interface IYarnToPropertyLineDao extends JpaRepository<YarnToPropertyLine, String> {

    YarnToPropertyLine findFirstByYarnIdAndYarnPropertyLineYarnPropertyId(String yarnId, String yarnPropertyId);

    List<YarnToPropertyLine> findByYarnId(String yarnId);
}
