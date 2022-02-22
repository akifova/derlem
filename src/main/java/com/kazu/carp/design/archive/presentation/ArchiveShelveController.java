package com.kazu.carp.design.archive.presentation;

import com.kazu.carp.app.exception.design.OnlyProductsCanBeRetiredException;
import com.kazu.carp.design.archive.business.IArchiveShelveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author akifova
 * 17.07.2021
 */
@RestController
@RequestMapping("/archive/shelve")
public class ArchiveShelveController {
    private static final Logger logger = LoggerFactory.getLogger(ArchiveShelveController.class);

    final IArchiveShelveService service;

    public ArchiveShelveController(IArchiveShelveService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/convert-to-stock/{archiveId}")
    public ResponseEntity convertToStock(@PathVariable String archiveId) {
        service.convertToStock(archiveId);
        return ResponseEntity.accepted().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/convert-to-unusable/{archiveId}")
    public ResponseEntity convertToUnusable(@PathVariable String archiveId) {
        service.convertToUnusable(archiveId);
        return ResponseEntity.accepted().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/convert-to-retired/{archiveId}")
    public ResponseEntity convertToRetired(@PathVariable String archiveId) throws OnlyProductsCanBeRetiredException {
        service.convertToRetired(archiveId);
        return ResponseEntity.accepted().build();
    }
}