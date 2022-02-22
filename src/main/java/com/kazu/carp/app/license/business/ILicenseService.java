package com.kazu.carp.app.license.business;

import com.kazu.carp.app.license.dto.LicenseDto;

import java.util.List;

/**
 * @author akifova
 * 05.10.2021
 */
public interface ILicenseService {
    void addLicense(LicenseDto licenseDto);
    List<LicenseDto> getActiveLicenses();
    List<LicenseDto> getAllLicenses();
    boolean verifyLicense();

}
