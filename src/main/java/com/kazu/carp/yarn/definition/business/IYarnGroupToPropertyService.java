package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.dto.YarnGroupToPropertyDto;

import java.util.List;

/**
 * @author akifova
 * 06.05.2021
 */
public interface IYarnGroupToPropertyService {

    YarnGroupToPropertyDto findOne(String id);

    List<YarnGroupToPropertyDto> findByGroupId(String groupId);

    void delete(String id);

    YarnGroupToPropertyDto create(YarnGroupToPropertyDto yarnGroupToPropertyDto);
}
