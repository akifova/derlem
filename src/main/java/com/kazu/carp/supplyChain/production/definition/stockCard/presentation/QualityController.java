package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.IQualityService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.QualityDto;
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
 * 06.02.2021
 */
@RestController
@RequestMapping("/scm/production/definition/stock-card/quality")
@Tag(name = "Kalite Tanımı", description = "")
public class QualityController {
    private static final Logger logger = LoggerFactory.getLogger(QualityController.class);

    final IQualityService qualityService;

    public QualityController(IQualityService qualityService) {
        this.qualityService = qualityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<QualityDto> create(@Valid @RequestBody QualityDto qualityDto) {
        QualityDto dto = qualityService.create(qualityDto);
        return new ResponseEntity<QualityDto>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody QualityDto qualityDto) {
        qualityService.update(qualityDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public QualityDto findOne(@PathVariable String id) {
        return qualityService.findOne(id);
    }

    @GetMapping("/")
    public List<QualityDto> findAll() {
        return qualityService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        qualityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<QualityDto> search(@RequestParam(value = "sp") String sp) {
        return qualityService.search(sp);
    }
}