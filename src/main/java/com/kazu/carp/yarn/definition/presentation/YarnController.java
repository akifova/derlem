package com.kazu.carp.yarn.definition.presentation;

import com.kazu.carp.app.exception.yarn.YarnPropertiesNotAsDefinedInYarnGroup;
import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.yarn.definition.business.IYarnService;
import com.kazu.carp.yarn.definition.dto.YarnActionType;
import com.kazu.carp.yarn.definition.dto.YarnDto;
import com.kazu.carp.yarn.definition.dto.YarnUsageType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author akifova
 * 05.05.2021
 */
@RestController
@RequestMapping("/yarn")
@Tag(name = "İplik Tanımı", description = "")
public class YarnController {
    private static final Logger logger = LoggerFactory.getLogger(YarnController.class);

    final IYarnService service;

    public YarnController(IYarnService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<YarnDto> create(@Valid @RequestBody YarnDto yarnDto) throws YarnPropertiesNotAsDefinedInYarnGroup {
        YarnDto dto = service.create(yarnDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody YarnDto yarnDto) {
        service.update(yarnDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<YarnDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public YarnDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usage-types")
    public List<YarnUsageType> usageTypes() {
        return Arrays.asList(YarnUsageType.values());
    }

    @GetMapping("/action-types")
    public List<YarnActionType> actionTypes() {
        return Arrays.asList(YarnActionType.values());
    }

    @GetMapping("/search")
    public List<YarnDto> search(@RequestParam(value = "sp") String sp) {
        return service.search(sp);
    }
}