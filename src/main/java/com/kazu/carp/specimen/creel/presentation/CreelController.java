package com.kazu.carp.specimen.creel.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.specimen.creel.business.ICreelService;
import com.kazu.carp.specimen.creel.dto.CreelDto;
import com.kazu.carp.specimen.creel.dto.ICreelMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author akifova
 * 07.05.2021
 */
@RestController
@RequestMapping("/specimen/creel")
@Tag(name = "Cağ Dizilim formu", description = "")
public class CreelController {
    private static final Logger logger = LoggerFactory.getLogger(CreelController.class);

    final ICreelService service;
    final ICreelMapper mapper;

    public CreelController(ICreelService service, ICreelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<CreelDto> create(@Valid @RequestBody CreelDto creelDto) {
        CreelDto dto = service.create(creelDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody CreelDto creelDto) {
        service.update(creelDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<CreelDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/get-by-design-plan-id/{designPlanId}")
    public List<CreelDto> findByDesignPlanId(@PathVariable String designPlanId) {
        return service.findByDesignPlanId(designPlanId);
    }

    @GetMapping("/get-by-archive-id/{archiveId}")
    public CreelDto findByArchiveId(@PathVariable String archiveId) {
        return service.findByArchiveId(archiveId);
    }

    @GetMapping("/{id}")
    public CreelDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}