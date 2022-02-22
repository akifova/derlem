package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.IEdgeService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.EdgeDto;
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
 * 10.02.2021
 */
@RestController
@RequestMapping("/scm/production/definition/stock-card/edge")
@Tag(name = "Kenar Formu Tanımı", description = "")
public class EdgeController {
    private static final Logger logger = LoggerFactory.getLogger(EdgeController.class);

    final IEdgeService edgeService;

    public EdgeController(IEdgeService edgeService) {
        this.edgeService = edgeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<EdgeDto> create(@Valid @RequestBody EdgeDto edgeDto) {
        EdgeDto dto = edgeService.create(edgeDto);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody EdgeDto edgeDto) {
        edgeService.update(edgeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<EdgeDto> findAll() {
        return edgeService.findAll();
    }

    @GetMapping("/{id}")
    public EdgeDto findOne(@PathVariable String id) {
        return edgeService.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        edgeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<EdgeDto> search(@RequestParam(value = "sp") String sp) {
        return edgeService.search(sp);
    }
}