package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.ICollectionService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.CollectionDto;
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
@RequestMapping("/scm/production/definition/stock-card/collection")
@Tag(name = "Koleksiyon Tanımı", description = "")
public class CollectionController {
    private static final Logger logger = LoggerFactory.getLogger(CollectionController.class);

    final ICollectionService collectionService;

    public CollectionController(ICollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<CollectionDto> create(@Valid @RequestBody CollectionDto collectionDto) {
        CollectionDto dto = collectionService.create(collectionDto);
        return new ResponseEntity<CollectionDto>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody CollectionDto collectionDto) {
        collectionService.update(collectionDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public CollectionDto findOne(@PathVariable String id) {
        return collectionService.findOne(id);
    }

    @GetMapping("/")
    public List<CollectionDto> findAll() {
        return collectionService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        collectionService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<CollectionDto> search(@RequestParam(value = "sp") String sp) {
        return collectionService.search(sp);
    }
}