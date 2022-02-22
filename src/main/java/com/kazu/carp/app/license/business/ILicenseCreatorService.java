package com.kazu.carp.app.license.business;

import com.kazu.carp.app.license.dto.LicenseDto;

/**
 * @author akifova
 * 01.10.2021
 */
public interface ILicenseCreatorService {
    LicenseDto create(LicenseDto licenseDto);
}
