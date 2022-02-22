package com.kazu.carp.common.uom.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.common.uom.business.IUOMService;
import com.kazu.carp.common.uom.dto.UnitOfMeasureDto;
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
 * 29.04.2021
 */
@RestController
@RequestMapping("/common/uom")
@Tag(name = "Ölçü Birimleri", description = "")
public class UnitOfMeasureController {
    private static final Logger logger = LoggerFactory.getLogger(UnitOfMeasureController.class);

    final IUOMService service;

    public UnitOfMeasureController(IUOMService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<UnitOfMeasureDto> create(@Valid @RequestBody UnitOfMeasureDto unitOfMeasureDto) {
        UnitOfMeasureDto dto = service.create(unitOfMeasureDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody UnitOfMeasureDto unitOfMeasureDto) {
        service.update(unitOfMeasureDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<UnitOfMeasureDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UnitOfMeasureDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<UnitOfMeasureDto> search(@RequestParam(value = "sp") String sp) {
        return service.search(sp);
    }
}