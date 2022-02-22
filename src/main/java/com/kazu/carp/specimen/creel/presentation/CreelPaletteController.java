package com.kazu.carp.specimen.creel.presentation;

import com.kazu.carp.specimen.creel.business.ICreelPaletteService;
import com.kazu.carp.specimen.creel.dto.CreelPaletteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author akifova
 * 19.07.2021
 */
@RestController
@RequestMapping("/creel/palette")
public class CreelPaletteController {
    private static final Logger logger = LoggerFactory.getLogger(CreelPaletteController.class);

    final ICreelPaletteService service;

    public CreelPaletteController(ICreelPaletteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public CreelPaletteDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping("/get-by-creel-id/{creelId}")
    public List<CreelPaletteDto> findByCreelId(@PathVariable String creelId) {
        return service.findByCreelId(creelId);
    }
}