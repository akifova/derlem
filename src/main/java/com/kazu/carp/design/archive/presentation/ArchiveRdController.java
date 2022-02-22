package com.kazu.carp.design.archive.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazu.carp.app.exception.common.FieldsCanNotBeNullException;
import com.kazu.carp.design.archive.business.IArchiveRdService;
import com.kazu.carp.design.archive.business.IArchiveService;
import com.kazu.carp.design.archive.dto.ArchiveDto;
import com.kazu.carp.design.archive.dto.ArchiveStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author akifova
 * 29.06.2021
 */
@RestController
@RequestMapping("/archive/rd")
public class ArchiveRdController {
    private static final Logger logger = LoggerFactory.getLogger(ArchiveRdController.class);

    final ObjectMapper objectMapper;
    private final IArchiveRdService service;
    final IArchiveService archiveService;

    public ArchiveRdController(ObjectMapper objectMapper, IArchiveRdService service, IArchiveService archiveService) {
        this.objectMapper = objectMapper;
        this.service = service;
        this.archiveService = archiveService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<ArchiveDto> create(
            @RequestParam MultipartFile design,
            @RequestParam MultipartFile[] empties,
            @RequestParam MultipartFile[] scans,
            @RequestParam String archiveDto) throws IOException, FieldsCanNotBeNullException {
        ArchiveDto parentDto = objectMapper.readValue(archiveDto, ArchiveDto.class);
        ArchiveDto dto = archiveService.addDesign(design, empties, scans,
                parentDto, ArchiveStatus.RD, false);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-plan-id/{planId}")
    public List<ArchiveDto> findAll(@PathVariable String planId) {
        return service.findByPlanId(planId);
    }

    @GetMapping("/{id}")
    public ArchiveDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }
}