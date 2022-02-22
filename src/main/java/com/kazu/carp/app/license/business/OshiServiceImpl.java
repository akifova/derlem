package com.kazu.carp.app.license.business;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

/**
 * @author akifova
 * 09.10.2021
 */
@Service
public class OshiServiceImpl implements IOshiService {
    private SystemInfo si = new SystemInfo();

    @Override
    @Cacheable("macAddress")
    public String getMacAddress() {
        return si.getHardware().getNetworkIFs().stream()
                .filter(f-> !StringUtils.isEmpty(f.getMacaddr()) && (f.getIPv4addr() != null && f.getIPv4addr().length > 0))
                .map(NetworkIF::getMacaddr)
                .findFirst().orElse(null);
    }

    @Override
    @Cacheable("processorId")
    public String getProcessorId() {
        return si.getHardware().getProcessor().getProcessorIdentifier().getProcessorID();
    }

    @Override
    @Cacheable("baseBoardSerialNumber")
    public String getBaseBoardSerialNumber() {
        return si.getHardware().getComputerSystem().getBaseboard().getSerialNumber();
    }
}
