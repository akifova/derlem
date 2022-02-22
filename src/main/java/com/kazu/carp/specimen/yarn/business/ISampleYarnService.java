package com.kazu.carp.specimen.yarn.business;

import com.kazu.carp.specimen.yarn.domain.SampleYarn;
import com.kazu.carp.specimen.yarn.dto.SampleYarnDto;

import java.util.List;

/**
 * @author akifova
 * 30.04.2021
 */
public interface ISampleYarnService {
    SampleYarnDto create(SampleYarnDto sampleYarnDto);

    SampleYarnDto update(SampleYarnDto sampleYarnDto);

    List<SampleYarnDto> findAll();

    SampleYarnDto findOne(String id);

    void delete(String id);

    SampleYarn getById(String id);

    List<SampleYarnDto> search(String sp);
}
