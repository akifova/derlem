package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnPropertyLine;
import com.kazu.carp.yarn.definition.dto.YarnPropertyLineDto;

import java.util.List;

/**
 * @author akifova
 * 05.05.2021
 */
public interface IYarnPropertyLineService {
    YarnPropertyLineDto create(YarnPropertyLineDto yarnPropertyLineDto);

    void update(YarnPropertyLineDto yarnPropertyLineDto);

    List<YarnPropertyLineDto> findAll();

    YarnPropertyLineDto findOne(String id);

    void delete(String id);

    YarnPropertyLine getById(String id);

    List<YarnPropertyLineDto> getByPropertyId(String propertyId);
}
