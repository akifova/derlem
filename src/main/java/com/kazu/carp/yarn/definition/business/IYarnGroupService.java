package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnGroup;
import com.kazu.carp.yarn.definition.dto.YarnGroupDto;

import java.util.List;

/**
 * @author akifova
 * 01.05.2021
 */
public interface IYarnGroupService {
    YarnGroupDto create(YarnGroupDto yarnGroupDto);

    void update(YarnGroupDto yarnGroupDto);

    List<YarnGroupDto> findAll();

    YarnGroupDto findOne(String id);

    void delete(String id);

    YarnGroup getById(String id);

    List<YarnGroupDto> search(String sp);
}
