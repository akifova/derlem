package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.app.exception.yarn.YarnPropertiesNotAsDefinedInYarnGroup;
import com.kazu.carp.yarn.definition.domain.Yarn;
import com.kazu.carp.yarn.definition.dto.YarnDto;

import java.util.List;

/**
 * @author akifova
 * 05.05.2021
 */
public interface IYarnService {
    YarnDto create(YarnDto yarnDto) throws YarnPropertiesNotAsDefinedInYarnGroup;

    void update(YarnDto yarnDto);

    List<YarnDto> findAll();

    YarnDto findOne(String id);

    void delete(String id);

    Yarn getById(String id);

    List<YarnDto> search(String sp);
}
