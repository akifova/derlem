package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.IShapeService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.ShapeDto;
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
 * 11.02.2021
 */
@RestController
@RequestMapping("/scm/production/definition/stock-card/shape")
@Tag(name = "Şekil Tanımı", description = "")
public class ShapeController {
    private static final Logger logger = LoggerFactory.getLogger(ShapeController.class);

    final IShapeService shapeService;

    public ShapeController(IShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<ShapeDto> create(@Valid @RequestBody ShapeDto shapeDto) {
        ShapeDto dto = shapeService.create(shapeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody ShapeDto shapeDto) {
        shapeService.update(shapeDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<ShapeDto> findAll() {
        return shapeService.findAll();
    }

    @GetMapping("/{id}")
    public ShapeDto findOne(@PathVariable String id) {
        return shapeService.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        shapeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<ShapeDto> search(@RequestParam(value = "sp") String sp) {
        return shapeService.search(sp);
    }
}