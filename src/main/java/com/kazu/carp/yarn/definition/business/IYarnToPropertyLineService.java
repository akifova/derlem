package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.dto.YarnToPropertyLineDto;

import java.util.List;

/**
 * @author akifova
 * 06.05.2021
 */
public interface IYarnToPropertyLineService {
    YarnToPropertyLineDto update(YarnToPropertyLineDto yarnToPropertyLineDto);

    YarnToPropertyLineDto findOne(String id);

    List<YarnToPropertyLineDto> getByYarnId(String yarnId);
}
