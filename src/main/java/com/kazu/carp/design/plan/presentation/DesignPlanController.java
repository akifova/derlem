package com.kazu.carp.design.plan.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazu.carp.design.plan.business.IDesignPlanService;
import com.kazu.carp.design.plan.dto.DesignPlanDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author akifova
 * 14.02.2021
 */
@RestController
@RequestMapping("/design/plan")
@Tag(name = "Tasarım Projesi", description = "Tasarım Projesi Oluşturma")
public class DesignPlanController {

    final IDesignPlanService designPlanService;
    final ObjectMapper objectMapper;

    public DesignPlanController(IDesignPlanService designPlanService, ObjectMapper objectMapper) {
        this.designPlanService = designPlanService;
        this.objectMapper = objectMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DesignPlanDto> create(@RequestParam MultipartFile[] files,
                                                @RequestParam String designPlanDto) throws JsonProcessingException {
        DesignPlanDto dto = designPlanService.create(objectMapper.readValue(designPlanDto, DesignPlanDto.class), Arrays.asList(files));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@RequestBody DesignPlanDto designPlanDto) {
        designPlanService.update(designPlanDto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{planId}")
    public ResponseEntity close(@PathVariable String planId) {
        designPlanService.close(planId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<DesignPlanDto> findAll() {
        return designPlanService.findAll();
    }

    @GetMapping("/actives")
    public List<DesignPlanDto> findActives() {
        return designPlanService.findActives();
    }

    @GetMapping("/{id}")
    public DesignPlanDto findOne(@PathVariable String id) {
        return designPlanService.findOne(id);
    }

    @GetMapping("/search")
    public List<DesignPlanDto> search(@RequestParam(value = "sp") String sp) {
        return designPlanService.search(sp);
    }
}