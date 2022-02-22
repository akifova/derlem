package com.kazu.carp.yarn.definition.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.yarn.definition.business.IYarnToPropertyLineService;
import com.kazu.carp.yarn.definition.dto.YarnToPropertyLineDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akifova
 * 05.05.2021
 */
@RestController
@RequestMapping("/yarn/to-property-line")
@Tag(name = "İplik Tanımına Ait Özellikler", description = "")
public class YarnToPropertyLineController {
    private static final Logger logger = LoggerFactory.getLogger(YarnToPropertyLineController.class);

    final IYarnToPropertyLineService service;

    public YarnToPropertyLineController(IYarnToPropertyLineService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity<YarnToPropertyLineDto> update(@Validated(OnUpdate.class) @RequestBody YarnToPropertyLineDto yarnToPropertyLineDto) {
        YarnToPropertyLineDto dto = service.update(yarnToPropertyLineDto);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public YarnToPropertyLineDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping("/get-by-yarn-id/{yarnId}")
    public List<YarnToPropertyLineDto> getLines(@PathVariable String yarnId) {
        return service.getByYarnId(yarnId);
    }
}