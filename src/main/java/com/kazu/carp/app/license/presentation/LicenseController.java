package com.kazu.carp.app.license.presentation;

import com.kazu.carp.app.license.business.ILicenseService;
import com.kazu.carp.app.license.business.IOshiService;
import com.kazu.carp.app.license.dto.LicenseDto;
import com.kazu.carp.common.util.dto.KeyValueDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author akifova
 * 05.10.2021
 */
@RestController
@RequestMapping("/license")
@Tag(name = "Uygulama Lisans İşlemleri", description = "")
public class LicenseController {

    final ILicenseService service;
    final IOshiService oshiService;

    public LicenseController(ILicenseService service, IOshiService oshiService) {
        this.service = service;
        this.oshiService = oshiService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/add")
    public ResponseEntity update(@RequestBody LicenseDto licenseDto) {
        service.addLicense(licenseDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/actives")
    public List<LicenseDto> getActiveLicenses() {
        return service.getActiveLicenses();
    }

    @GetMapping("/all")
    public List<LicenseDto> getAllLicenses() {
        return service.getAllLicenses();
    }

    @GetMapping("/mac-address")
    public String getMacAddress() {
        return oshiService.getMacAddress();
    }

    @GetMapping("/processor-id")
    public String getProcessorId() {
        return oshiService.getProcessorId();
    }

    @GetMapping("/base-board-serial-number")
    public String getBaseBoardSerialNumber() {
        return oshiService.getBaseBoardSerialNumber();
    }

    @GetMapping("/server-license-info")
    public List<KeyValueDto> getServerLicenseInfo() {
        KeyValueDto macAddress = KeyValueDto.builder().key("macAddress").value(oshiService.getMacAddress()).build();
        KeyValueDto processorId = KeyValueDto.builder().key("processorId").value(oshiService.getProcessorId()).build();
        KeyValueDto baseBoardSerialNumber = KeyValueDto.builder().key("baseBoardSerialNumber").value(oshiService.getBaseBoardSerialNumber()).build();

        return Arrays.asList(macAddress, processorId, baseBoardSerialNumber);
    }
}