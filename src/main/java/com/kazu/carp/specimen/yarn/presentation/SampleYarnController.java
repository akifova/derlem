package com.kazu.carp.specimen.yarn.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.specimen.yarn.business.ISampleYarnService;
import com.kazu.carp.specimen.yarn.dto.SampleYarnDto;
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
 * 30.04.2021
 */
@RestController
@RequestMapping("/specimen/sample-yarn")
@Tag(name = "Numune İplik", description = "")
public class SampleYarnController {
    private static final Logger logger = LoggerFactory.getLogger(SampleYarnController.class);

    final ISampleYarnService service;

    public SampleYarnController(ISampleYarnService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<SampleYarnDto> create(@Valid @RequestBody SampleYarnDto sampleYarnDto) {
        SampleYarnDto dto = service.create(sampleYarnDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody SampleYarnDto sampleYarnDto) {
        SampleYarnDto dto = service.update(sampleYarnDto);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public List<SampleYarnDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SampleYarnDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<SampleYarnDto> search(@RequestParam(value = "sp") String sp) {
        return service.search(sp);
    }
}