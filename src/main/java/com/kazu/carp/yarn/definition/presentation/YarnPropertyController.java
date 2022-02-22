package com.kazu.carp.yarn.definition.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.yarn.definition.business.IYarnPropertyService;
import com.kazu.carp.yarn.definition.dto.YarnPropertyDto;
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
 * 05.05.2021
 */
@RestController
@RequestMapping("/yarn/property")
@Tag(name = "İplik Özelliği", description = "")
public class YarnPropertyController {
    private static final Logger logger = LoggerFactory.getLogger(YarnPropertyController.class);

    final IYarnPropertyService service;

    public YarnPropertyController(IYarnPropertyService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<YarnPropertyDto> create(@Valid @RequestBody YarnPropertyDto yarnPropertyDto) {
        YarnPropertyDto dto = service.create(yarnPropertyDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody YarnPropertyDto yarnPropertyDto) {
        service.update(yarnPropertyDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<YarnPropertyDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public YarnPropertyDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}