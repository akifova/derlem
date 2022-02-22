package com.kazu.carp.specimen.creel.presentation;

import com.kazu.carp.specimen.creel.business.ICreelPaletteLineToYarnService;
import com.kazu.carp.specimen.creel.dto.CreelPaletteLineToYarnDto;
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
@RequestMapping("/creel/latte")
@Tag(name = "Cağ Dizim Formu, Palet Satırı Latte Tanımları", description = "")
public class CreelPaletteLineLatteController {
    private static final Logger logger = LoggerFactory.getLogger(CreelPaletteLineLatteController.class);

    final ICreelPaletteLineToYarnService service;

    public CreelPaletteLineLatteController(ICreelPaletteLineToYarnService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<CreelPaletteLineToYarnDto> create(@RequestBody CreelPaletteLineToYarnDto creelPaletteLineToYarnDto) {
        CreelPaletteLineToYarnDto dto = service.create(creelPaletteLineToYarnDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@RequestBody CreelPaletteLineToYarnDto creelPaletteLineToYarnDto) {
        service.update(creelPaletteLineToYarnDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/list/{lineId}")
    public List<CreelPaletteLineToYarnDto> findByPaletteLineId(@PathVariable String lineId) {
        return service.findByPaletteLineId(lineId);
    }

    @GetMapping("/{id}")
    public CreelPaletteLineToYarnDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}