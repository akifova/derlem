package com.kazu.carp.design.archive.business;

import com.kazu.carp.app.exception.common.FieldsCanNotBeNullException;
import com.kazu.carp.design.archive.dto.ArchiveDto;

/**
 * @author akifova
 * 04.07.2021
 */
public interface IArchiveSampleService {
    void convertToSample(ArchiveDto archiveDto) throws FieldsCanNotBeNullException;
}
