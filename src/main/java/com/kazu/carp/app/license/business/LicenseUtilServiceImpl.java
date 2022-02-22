package com.kazu.carp.app.license.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.kazu.carp.app.license.dto.CryptoConstants.E;
import static com.kazu.carp.app.license.dto.CryptoConstants.PI;


/**
 * @author akifova
 * 01.10.2021
 */
@Service
public class LicenseUtilServiceImpl implements ILicenseUtilService {
    private static final Logger logger = LoggerFactory.getLogger(LicenseUtilServiceImpl.class);

    final ICryptoService cryptoService;

    public LicenseUtilServiceImpl(ICryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Override
    public String encryptDay(LocalDate endDate) {

        var day = BigDecimal.valueOf(endDate.getDayOfMonth());
        var v9 = BigDecimal.valueOf(9);
        var v2 = BigDecimal.valueOf(2);
        var g2 = day.pow(2);

        var dayVal = (g2.multiply(v9)).add(v2.multiply(PI).multiply(day)).add(E);
        return cryptoService.encrypt(dayVal.toEngineeringString());
    }

    @Override
    public String encryptMonth(LocalDate endDate) {

        var month = BigDecimal.valueOf(endDate.getMonthValue());
        var v13 = BigDecimal.valueOf(13);
        var v7 = BigDecimal.valueOf(7);
        var v9 = BigDecimal.valueOf(9);
        var a2 = month.pow(2);
        var monthVal = a2.multiply(v13).add(v7.multiply(PI).multiply(month).add(E.multiply(v9)));
        return cryptoService.encrypt(monthVal.toEngineeringString());
    }

    @Override
    public String encryptYear(LocalDate endDate) {
        var year = BigDecimal.valueOf(endDate.getYear() - 2000);
        var v3 = BigDecimal.valueOf(3);
        var y3 = year.pow(3);
        var y2 = year.pow(2);

        var yearVal = y3.multiply(v3).add(PI.multiply(y2)).add(E.multiply(year)).add(v3);

        return cryptoService.encrypt(yearVal.toEngineeringString());
    }

    @Override
    public String encryptMac1(String mac) {
        var mc = mac.split(":")[0];
        var m = hexToDec(mc);
        var v206 = BigDecimal.valueOf(206);
        var v7 = BigDecimal.valueOf(7);

        var mValue = m.multiply(v206).add(PI.multiply(v7));
        return cryptoService.encrypt(mValue.toEngineeringString());
    }

    @Override
    public String encryptMac2(String mac) {

        var mc = mac.split(":")[1];
        var m = hexToDec(mc);
        var v3 = BigDecimal.valueOf(3);
        var v17 = BigDecimal.valueOf(17);
        var r2 = m.pow(2);

        var mValue = r2.multiply(v17).add(m.multiply(v3)).add(PI);
        return cryptoService.encrypt(mValue.toEngineeringString());
    }

    @Override
    public String encryptMac3(String mac) {

        var mc = mac.split(":")[2];
        var m = hexToDec(mc);
        var v3 = BigDecimal.valueOf(3);
        var v24 = BigDecimal.valueOf(24);

        var mValue = m.multiply(v24).add(v3);
        return cryptoService.encrypt(mValue.toEngineeringString());
    }

    @Override
    public String encryptMac4(String mac) {

        var mc = mac.split(":")[3];
        var m = hexToDec(mc);
        var v10 = BigDecimal.valueOf(10);
        var v9 = BigDecimal.valueOf(9);
        var v7 = BigDecimal.valueOf(7);

        var mValue = (m.multiply(PI).multiply(v10)).add(m.multiply(v9)).add(v7);
        return cryptoService.encrypt(mValue.toEngineeringString());
    }

    @Override
    public String encryptMac5(String mac) {

        var mc = mac.split(":")[4];
        var m = hexToDec(mc);
        var v32 = BigDecimal.valueOf(32);
        var v9 = BigDecimal.valueOf(9);
        var u2 = m.pow(2);

        var mValue = u2.multiply(PI).multiply(v32).add(v9);
        return cryptoService.encrypt(mValue.toEngineeringString());
    }

    @Override
    public String encryptMac6(String mac) {
        var mc = mac.split(":")[5];
        var m = hexToDec(mc);
        var v23 = BigDecimal.valueOf(23);
        var v19 = BigDecimal.valueOf(19);
        var v2 = m.pow(2);

        var mValue = v2.multiply(v23).add(v19);
        return cryptoService.encrypt(mValue.toEngineeringString());
    }

    @Override
    public String encryptProcessorId(String processorId) {
        return cryptoService.encrypt(processorId);
    }

    @Override
    public String encryptBaseBoardSerialNumber(String baseBoardSerialNumber) {
        return cryptoService.encrypt(baseBoardSerialNumber);
    }

    private BigDecimal hexToDec(String hexValue) {
        return BigDecimal.valueOf(Integer.parseInt(hexValue, 16 ));
    }

}