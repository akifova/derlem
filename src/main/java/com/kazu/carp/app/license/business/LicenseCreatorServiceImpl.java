package com.kazu.carp.app.license.business;

import com.kazu.carp.app.license.dto.LicenseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author akifova
 * 01.10.2021
 */
@Service
public class LicenseCreatorServiceImpl implements ILicenseCreatorService {
    private static final Logger logger = LoggerFactory.getLogger(LicenseCreatorServiceImpl.class);

    final ILicenseUtilService utilService;

    public LicenseCreatorServiceImpl(ILicenseUtilService utilService) {
        this.utilService = utilService;
    }

    @Override
    public LicenseDto create(LicenseDto licenseDto) {

        LocalDate endDate = licenseDto.getEndDate();
        String macAddress = licenseDto.getMacAddress();
        String processorId = licenseDto.getProcessorId();
        String baseBoardSerialNumber = licenseDto.getBaseBoardSerialNumber();

        String encryptedDay = utilService.encryptDay(endDate);
        String encryptedMonth = utilService.encryptMonth(endDate);
        String encryptedYear = utilService.encryptYear(endDate);
        String encryptedMac1 = utilService.encryptMac1(macAddress);
        String encryptedMac2 = utilService.encryptMac2(macAddress);
        String encryptedMac3 = utilService.encryptMac3(macAddress);
        String encryptedMac4 = utilService.encryptMac4(macAddress);
        String encryptedMac5 = utilService.encryptMac5(macAddress);
        String encryptedMac6 = utilService.encryptMac6(macAddress);

        String encryptedProcessorId = utilService.encryptProcessorId(processorId);
        String encryptedBaseBoardSerialNumber = utilService.encryptBaseBoardSerialNumber(baseBoardSerialNumber);

        var license = String.format("%s%s%s%s%s%s%s%s%s%s%s",
                encryptedMac5, encryptedMac3, encryptedMonth,
                encryptedMac1,  encryptedYear,encryptedMac2,
                encryptedMac4, encryptedDay, encryptedMac6,
                encryptedProcessorId, encryptedBaseBoardSerialNumber);

        LicenseDto dto = LicenseDto.builder()
                .endDate(endDate)
                .macAddress(macAddress)
                .processorId(processorId)
                .baseBoardSerialNumber(baseBoardSerialNumber)
                .license(license)
                .build();

        return dto;
    }
}