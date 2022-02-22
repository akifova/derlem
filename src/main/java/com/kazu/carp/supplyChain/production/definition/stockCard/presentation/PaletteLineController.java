package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.supplyChain.production.definition.stockCard.business.IPaletteLineService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akifova
 * 07.02.2021
 */
@RestController
@RequestMapping("/scm/production/definition/stock-card/palette-line")
@Tag(name = "Palet Satırı Tanımı", description = "")
public class PaletteLineController {
    private static final Logger logger = LoggerFactory.getLogger(PaletteLineController.class);

    final IPaletteLineService paletteLineService;

    public PaletteLineController(IPaletteLineService paletteLineService) {
        this.paletteLineService = paletteLineService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<PaletteLineDto> create(@RequestBody PaletteLineDto paletteLineDto) {
        PaletteLineDto dto = paletteLineService.create(paletteLineDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@RequestBody PaletteLineDto paletteLineDto) {
        paletteLineService.update(paletteLineDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<PaletteLineDto> findAll() {
        return paletteLineService.findAll();
    }

    @GetMapping("/{id}")
    public PaletteLineDto findOne(@PathVariable String id) {
        return paletteLineService.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        paletteLineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find-by-palette-id/{paletteId}")
    public List<PaletteLineDto> findByPaletteId(@PathVariable String paletteId) {
        return paletteLineService.findByPaletteId(paletteId);
    }

    @GetMapping("/search")
    public List<PaletteLineDto> search(@RequestParam(value = "sp") String sp) {
        return paletteLineService.search(sp);
    }
}