package com.kazu.carp.common.address.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.common.address.business.IAddressService;
import com.kazu.carp.common.address.business.ICountryService;
import com.kazu.carp.common.address.business.ICountyService;
import com.kazu.carp.common.address.dto.AddressDto;
import com.kazu.carp.common.address.dto.CountryDto;
import com.kazu.carp.common.address.dto.CountyDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common/address")
@Tag(name = "Adres", description = "")
public class AddressController {

    final ICountyService countyService;
    final ICountryService countryService;
    final IAddressService service;

    public AddressController(ICountyService countyService, ICountryService countryService, IAddressService service) {
        this.countyService = countyService;
        this.countryService = countryService;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody AddressDto addressDto) {
        service.update(addressDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public AddressDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping("/get-by-firm/{firmId}")
    public List<AddressDto> getByFirm(@PathVariable String firmId) {
        return service.findByFirmId(firmId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/county/search")
    public List<CountyDto> searchCounty(@RequestParam(value = "sp") String sp) {
        return countyService.search(sp);
    }

    @GetMapping("/country/search")
    public List<CountryDto> searchCountry(@RequestParam(value = "sp") String sp) {
        return countryService.search(sp);
    }
}