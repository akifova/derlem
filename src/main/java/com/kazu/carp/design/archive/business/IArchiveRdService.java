package com.kazu.carp.design.archive.business;

import com.kazu.carp.design.archive.dto.ArchiveDto;

import java.util.List;

/**
 * @author akifova
 * 29.06.2021
 */
public interface IArchiveRdService {

    List<ArchiveDto> findByPlanId(String planId);

    ArchiveDto findOne(String id);
}
