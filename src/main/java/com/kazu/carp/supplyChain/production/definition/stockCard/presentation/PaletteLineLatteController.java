package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.supplyChain.production.definition.stockCard.business.IPaletteLineToYarnService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineToYarnDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.accepted;

/**
 * @author akifova
 * 08.02.2021
 */
@RestController
@RequestMapping("/scm/production/definition/stock-card/palette-line-latte")
@Tag(name = "Palet Satırı Latte Tanımı", description = "")
public class PaletteLineLatteController {
    private static final Logger logger = LoggerFactory.getLogger(PaletteLineLatteController.class);

    final IPaletteLineToYarnService paletteLineLatteService;

    public PaletteLineLatteController(IPaletteLineToYarnService paletteLineLatteService) {
        this.paletteLineLatteService = paletteLineLatteService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<PaletteLineToYarnDto> create(@RequestBody PaletteLineToYarnDto latteDto) {
        PaletteLineToYarnDto dto = paletteLineLatteService.create(latteDto);
        return new ResponseEntity<>(dto, CREATED);
    }

    @ResponseStatus(ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@RequestBody PaletteLineToYarnDto latteDto) {
        paletteLineLatteService.update(latteDto);
        return accepted().build();
    }

    @GetMapping("/")
    public List<PaletteLineToYarnDto> findAll() {
        return paletteLineLatteService.findAll();
    }

    @GetMapping("/{id}")
    public PaletteLineToYarnDto findOne(@PathVariable String id) {
        return paletteLineLatteService.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        paletteLineLatteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<PaletteLineToYarnDto> search(@RequestParam(value = "sp") String sp) {
        return paletteLineLatteService.search(sp);
    }
}