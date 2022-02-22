package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnType;
import com.kazu.carp.yarn.definition.dto.YarnTypeDto;

import java.util.List;

/**
 * @author akifova
 * 01.05.2021
 */
public interface IYarnTypeService {

    YarnTypeDto create(YarnTypeDto yarnTypeDto);

    void update(YarnTypeDto yarnTypeDto);

    List<YarnTypeDto> findAll();

    YarnTypeDto findOne(String id);

    void delete(String id);

    YarnType getById(String id);
}
