package com.kazu.carp.supplyChain.production.definition.stockCard.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.IDesignService;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.DesignDto;
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
 * 07.02.2021
 */
@RestController
@RequestMapping("/scm/production/definition/stock-card/design")
@Tag(name = "Desen Kodu Tanımı", description = "")
public class DesignController {
    private static final Logger logger = LoggerFactory.getLogger(DesignController.class);

    final IDesignService designService;

    public DesignController(IDesignService designService) {
        this.designService = designService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<DesignDto> create(@Valid @RequestBody DesignDto designDto) {
        DesignDto dto = designService.create(designDto);
        return new ResponseEntity<DesignDto>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody DesignDto designDto) {
        designService.update(designDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<DesignDto> findAll() {
        return designService.findAll();
    }

    @GetMapping("/{id}")
    public DesignDto findOne(@PathVariable String id) {
        return designService.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        designService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<DesignDto> search(@RequestParam(value = "sp") String sp) {
        return designService.search(sp);
    }
}