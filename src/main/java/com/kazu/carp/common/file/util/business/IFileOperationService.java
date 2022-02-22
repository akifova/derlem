package com.kazu.carp.common.file.util.business;

/**
 * @author akifova
 * 16.02.2021
 */
public interface IFileOperationService {
    void saveImageFileOnDisk(byte[] file, String fileAddress, String fileName);
    void deleteFile(String location, String fileName);
}
