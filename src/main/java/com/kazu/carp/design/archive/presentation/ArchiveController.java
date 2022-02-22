package com.kazu.carp.design.archive.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazu.carp.app.exception.common.FieldsCanNotBeNullException;
import com.kazu.carp.common.util.dto.KeyValueDto;
import com.kazu.carp.design.archive.business.IArchiveService;
import com.kazu.carp.design.archive.dto.ArchiveDto;
import com.kazu.carp.design.archive.dto.ArchiveStatus;
import com.kazu.carp.design.archive.dto.WeaveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 09.07.2021
 */
@RestController
@RequestMapping("/archive")
public class ArchiveController {
    private static final Logger logger = LoggerFactory.getLogger(ArchiveController.class);

    final IArchiveService service;
    final ObjectMapper objectMapper;

    public ArchiveController(IArchiveService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/convert-to-archive/{sampleId}")
    public ResponseEntity convertToArchive(@PathVariable String sampleId) throws FieldsCanNotBeNullException {
        service.convertToArchive(sampleId);
        return ResponseEntity.accepted().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-design")
    public ResponseEntity<ArchiveDto> addDesign(
            @RequestParam MultipartFile design,
            @RequestParam MultipartFile[] empties,
            @RequestParam MultipartFile[] scans,
            @RequestParam String archiveDto) throws IOException, FieldsCanNotBeNullException {
        ArchiveDto parentDto = objectMapper.readValue(archiveDto, ArchiveDto.class);
        ArchiveDto dto = service.addDesign(design, empties, scans,
                parentDto, parentDto.getArchiveStatus(), true);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ArchiveDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping("/search")
    public List<ArchiveDto> search(@RequestParam(value = "sp") String sp) {
        return service.search(sp);
    }

    @GetMapping("/statuses")
    public List<KeyValueDto> statuses() {
        return Arrays.stream(ArchiveStatus.values())
                .map(m-> new KeyValueDto(m.getName(), m.getDescription()))
                .collect(Collectors.toList());
    }

    @GetMapping("/weave-types")
    public List<KeyValueDto> weaveTypes() {
        return Arrays.stream(WeaveType.values())
                .map(m-> new KeyValueDto(m.getValue(), m.getDescription()))
                .collect(Collectors.toList());
    }
}