package com.kazu.carp.design.archive.business;

import com.kazu.carp.app.exception.common.FieldsCanNotBeNullException;
import com.kazu.carp.design.archive.dto.ArchiveDto;
import com.kazu.carp.design.archive.dto.ArchiveStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author akifova
 * 09.07.2021
 */
public interface IArchiveService {
    void convertToArchive(String sampleId) throws FieldsCanNotBeNullException;

    ArchiveDto findOne(String id);

    List<ArchiveDto> search(String sp);

    ArchiveDto addDesign(MultipartFile design, MultipartFile[] empties, MultipartFile[] scans,
                         ArchiveDto readValue, ArchiveStatus newStatus, boolean setVersion)
            throws IOException, FieldsCanNotBeNullException;
}
