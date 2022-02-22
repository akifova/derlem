package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnProperty;
import com.kazu.carp.yarn.definition.dto.YarnPropertyDto;

import java.util.List;

/**
 * @author akifova
 * 05.05.2021
 */
public interface IYarnPropertyService {
    YarnPropertyDto create(YarnPropertyDto yarnPropertyDto);

    void update(YarnPropertyDto yarnPropertyDto);

    List<YarnPropertyDto> findAll();

    YarnPropertyDto findOne(String id);

    void delete(String id);

    YarnProperty getById(String id);
}
