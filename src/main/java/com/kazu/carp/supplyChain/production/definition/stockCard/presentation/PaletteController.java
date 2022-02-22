package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.supplyChain.production.definition.stockCard.business.IPaletteService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteFindSimilarDto;
import com.kazu.carp.yarn.definition.dto.YarnDto;
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
@RequestMapping("/scm/production/definition/stock-card/palette")
@Tag(name = "Palet Tanımı", description = "")
public class PaletteController {
    private static final Logger logger = LoggerFactory.getLogger(PaletteController.class);

    final IPaletteService paletteService;

    public PaletteController(IPaletteService paletteService) {
        this.paletteService = paletteService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<PaletteDto> create(@RequestBody PaletteDto paletteDto) {
        PaletteDto dto = paletteService.create(paletteDto);
        return new ResponseEntity<PaletteDto>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@RequestBody PaletteDto paletteDto) {
        paletteService.update(paletteDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<PaletteDto> findAll() {
        return paletteService.findAll();
    }

    @GetMapping("/{id}")
    public PaletteDto findOne(@PathVariable String id) {
        return paletteService.findOne(id);
    }

    @GetMapping("/get-yarns/{id}")
    public List<YarnDto> getYarns(@PathVariable String id) {
        return paletteService.getYarns(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        paletteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<PaletteDto> search(@RequestParam(value = "sp") String sp) {
        return paletteService.search(sp);
    }

    @GetMapping("/find-similar")
    public List<PaletteFindSimilarDto> findSimilar(@RequestParam List<String> colorCodes) {
        return paletteService.findSimilar(colorCodes);
    }
}