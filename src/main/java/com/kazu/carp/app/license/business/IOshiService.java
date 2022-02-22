package com.kazu.carp.app.license.business;

/**
 * @author akifova
 * 09.10.2021
 */
public interface IOshiService {
    String getMacAddress();
    String getProcessorId();
    String getBaseBoardSerialNumber();
}
