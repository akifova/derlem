package com.kazu.carp.specimen.creel.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.specimen.creel.business.ICreelQualityService;
import com.kazu.carp.specimen.creel.dto.CreelQualityDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akifova
 * 08.05.2021
 */
@RestController
@RequestMapping("/creel/quality")
@Tag(name = "Cağ Dizim Formu Kalite Tanımı", description = "")
public class CreelQualityController {
    private static final Logger logger = LoggerFactory.getLogger(CreelQualityController.class);

    final ICreelQualityService service;

    public CreelQualityController(ICreelQualityService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity<CreelQualityDto> update(@Validated(OnUpdate.class) @RequestBody CreelQualityDto creelQualityDto) {
        CreelQualityDto dto = service.update(creelQualityDto);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/{creelId}")
    public List<CreelQualityDto> findByCreelId(@PathVariable String creelId) {
        return service.findByCreelId(creelId);
    }

    @GetMapping("/{id}")
    public CreelQualityDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }
}