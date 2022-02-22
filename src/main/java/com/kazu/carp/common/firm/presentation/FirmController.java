package com.kazu.carp.common.firm.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.common.address.dto.AddressDto;
import com.kazu.carp.common.firm.business.IFirmService;
import com.kazu.carp.common.firm.dto.FirmDto;
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
 * 29.04.2021
 */
@RestController
@RequestMapping("/common/firm")
@Tag(name = "Firma", description = "Tedarikçi, Müşteri, Fason")
public class FirmController {
    private static final Logger logger = LoggerFactory.getLogger(FirmController.class);

    final IFirmService service;

    public FirmController(IFirmService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<FirmDto> create(@Valid @RequestBody FirmDto firmDto) {
        firmDto = service.create(firmDto);
        return new ResponseEntity<>(firmDto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody FirmDto firmDto) {
        service.update(firmDto);
        return ResponseEntity.accepted().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/update-address/{firmId}")
    public ResponseEntity updateAddress(@Validated @RequestBody AddressDto addressDto,
                                        @PathVariable String firmId) {
        service.updateAddress(firmId, addressDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<FirmDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public FirmDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<FirmDto> search(@RequestParam(value = "sp") String sp) {
        return service.search(sp);
    }
}