package com.kazu.carp.yarn.definition.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.yarn.definition.business.IYarnPropertyLineService;
import com.kazu.carp.yarn.definition.dto.YarnPropertyLineDto;
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
@RequestMapping("/yarn/property-line")
@Tag(name = "İplik Özellik Detayı", description = "")
public class YarnPropertyLineController {
    private static final Logger logger = LoggerFactory.getLogger(YarnPropertyLineController.class);

    final IYarnPropertyLineService service;

    public YarnPropertyLineController(IYarnPropertyLineService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<YarnPropertyLineDto> create(@Valid @RequestBody YarnPropertyLineDto yarnPropertyLineDto) {
        YarnPropertyLineDto dto = service.create(yarnPropertyLineDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody YarnPropertyLineDto yarnPropertyLineDto) {
        service.update(yarnPropertyLineDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<YarnPropertyLineDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public YarnPropertyLineDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping("/get-by-property-id/{propertyId}")
    public List<YarnPropertyLineDto> getByPropertyId(@PathVariable String propertyId) {
        return service.getByPropertyId(propertyId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}