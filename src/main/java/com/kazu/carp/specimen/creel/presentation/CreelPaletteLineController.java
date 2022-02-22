package com.kazu.carp.specimen.creel.presentation;

import com.kazu.carp.specimen.creel.business.ICreelPaletteLineService;
import com.kazu.carp.specimen.creel.dto.CreelPaletteLineDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akifova
 * 12.05.2021
 */
@RestController
@RequestMapping("/creel/palette-line")
@Tag(name = "Cağ Dizim Formu Palet Satırı", description = "")
public class CreelPaletteLineController {
    private static final Logger logger = LoggerFactory.getLogger(CreelPaletteLineController.class);

    final ICreelPaletteLineService service;

    public CreelPaletteLineController(ICreelPaletteLineService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<CreelPaletteLineDto> create(@RequestBody CreelPaletteLineDto creelPaletteLineDto) {
        CreelPaletteLineDto dto = service.create(creelPaletteLineDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@RequestBody CreelPaletteLineDto creelPaletteLineDto) {
        service.update(creelPaletteLineDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/list/{paletteId}")
    public List<CreelPaletteLineDto> findByPaletteId(@PathVariable String paletteId) {
        return service.findByPaletteId(paletteId);
    }

    @GetMapping("/{id}")
    public CreelPaletteLineDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}