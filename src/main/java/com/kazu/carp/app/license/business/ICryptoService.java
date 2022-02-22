package com.kazu.carp.app.license.business;

/**
 * @author akifova
 * 01.10.2021
 */
public interface ICryptoService {
    String encrypt(String value);
    String decrypt(String encryptedValue);
}
