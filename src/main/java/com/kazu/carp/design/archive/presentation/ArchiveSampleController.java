package com.kazu.carp.design.archive.presentation;

import com.kazu.carp.app.exception.common.FieldsCanNotBeNullException;
import com.kazu.carp.design.archive.business.IArchiveSampleService;
import com.kazu.carp.design.archive.dto.ArchiveDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author akifova
 * 04.07.2021
 */
@RestController
@RequestMapping("/archive/sample")
public class ArchiveSampleController {
    private static final Logger logger = LoggerFactory.getLogger(ArchiveSampleController.class);

    final IArchiveSampleService service;

    public ArchiveSampleController(IArchiveSampleService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/convert-to-sample")
    public ResponseEntity convertToSample(@RequestBody ArchiveDto archiveDto) throws FieldsCanNotBeNullException {
        service.convertToSample(archiveDto);
        return ResponseEntity.accepted().build();
    }
}