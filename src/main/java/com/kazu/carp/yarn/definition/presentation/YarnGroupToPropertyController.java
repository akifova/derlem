package com.kazu.carp.yarn.definition.presentation;

import com.kazu.carp.yarn.definition.business.IYarnGroupToPropertyService;
import com.kazu.carp.yarn.definition.dto.YarnGroupToPropertyDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akifova
 * 06.05.2021
 */
@RestController
@RequestMapping("/yarn/group-to-property")
@Tag(name = "İplik Grubunun Kullanacağı Özellikler", description = "")
public class YarnGroupToPropertyController {
    private static final Logger logger = LoggerFactory.getLogger(YarnGroupToPropertyController.class);

    final IYarnGroupToPropertyService service;

    public YarnGroupToPropertyController(IYarnGroupToPropertyService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<YarnGroupToPropertyDto> update(@RequestBody YarnGroupToPropertyDto yarnGroupToPropertyDto) {
        YarnGroupToPropertyDto dto = service.create(yarnGroupToPropertyDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public YarnGroupToPropertyDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping("/get-by-group-id/{groupId}")
    public List<YarnGroupToPropertyDto> getProperties(@PathVariable String groupId) {
        return service.findByGroupId(groupId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}