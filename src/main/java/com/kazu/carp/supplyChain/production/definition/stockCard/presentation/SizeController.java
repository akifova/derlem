package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.ISizeService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.SizeDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.SizeType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author akifova
 * 10.02.2021
 */
@RestController
@RequestMapping("/scm/production/definition/stock-card/measure")
@Tag(name = "Ebat Tanımı", description = "")
public class SizeController {
    private static final Logger logger = LoggerFactory.getLogger(SizeController.class);

    final ISizeService measureService;

    public SizeController(ISizeService measureService) {
        this.measureService = measureService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<SizeDto> create(@Valid @RequestBody SizeDto sizeDto) {
        SizeDto dto = measureService.create(sizeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody SizeDto sizeDto) {
        measureService.update(sizeDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<SizeDto> findAll() {
        return measureService.findAll();
    }

    @GetMapping("/{id}")
    public SizeDto findOne(@PathVariable String id) {
        return measureService.findOne(id);
    }

    @GetMapping("/size-types")
    public List<SizeType> getSizeTypes() {
        return Arrays.asList(SizeType.values());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        measureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<SizeDto> search(@RequestParam(value = "sp") String sp) {
        return measureService.search(sp);
    }
}