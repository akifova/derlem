package com.kazu.carp.yarn.definition.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.yarn.definition.business.IYarnGroupService;
import com.kazu.carp.yarn.definition.dto.YarnGroupDto;
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
 * 01.05.2021
 */
@RestController
@RequestMapping("/yarn/group")
@Tag(name = "İpilk Grubu", description = "")
public class YarnGroupController {
    private static final Logger logger = LoggerFactory.getLogger(YarnGroupController.class);

    final IYarnGroupService service;

    public YarnGroupController(IYarnGroupService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<YarnGroupDto> create(@Valid @RequestBody YarnGroupDto yarnGroupDto) {
        YarnGroupDto dto = service.create(yarnGroupDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody YarnGroupDto yarnGroupDto) {
        service.update(yarnGroupDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<YarnGroupDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public YarnGroupDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<YarnGroupDto> search(@RequestParam(value = "sp") String sp) {
        return service.search(sp);
    }
}