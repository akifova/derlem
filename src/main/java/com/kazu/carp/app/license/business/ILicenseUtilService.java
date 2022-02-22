package com.kazu.carp.app.license.business;

import java.time.LocalDate;

/**
 * @author akifova
 * 01.10.2021
 */
public interface ILicenseUtilService {
    String encryptDay(LocalDate endDate);
    String encryptMonth(LocalDate endDate);
    String encryptYear(LocalDate endDate);
    String encryptMac1(String mac);
    String encryptMac2(String mac);
    String encryptMac3(String mac);
    String encryptMac4(String mac);
    String encryptMac5(String mac);
    String encryptMac6(String mac);
    String encryptProcessorId(String processorId);
    String encryptBaseBoardSerialNumber(String baseBoardSerialNumber);
}
