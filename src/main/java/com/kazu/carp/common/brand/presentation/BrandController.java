package com.kazu.carp.common.brand.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.common.brand.business.IBrandService;
import com.kazu.carp.common.brand.dto.BrandDto;
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
 * 18.04.2021
 */
@RestController
@RequestMapping("/common/brand")
@Tag(name = "Marka", description = "")
public class BrandController {
    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    final IBrandService brandService;

    public BrandController(IBrandService brandService) {
        this.brandService = brandService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<BrandDto> create(@Valid @RequestBody BrandDto brandDto) {
        var dto = brandService.create(brandDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody BrandDto brandDto) {
        brandService.update(brandDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<BrandDto> findAll() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    public BrandDto findOne(@PathVariable String id) {
        return brandService.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}