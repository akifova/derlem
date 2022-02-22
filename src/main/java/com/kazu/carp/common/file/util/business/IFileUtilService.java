package com.kazu.carp.common.file.util.business;

import com.kazu.carp.common.file.util.dto.ModuleFolder;
import com.kazu.carp.common.file.util.dto.ZipFileDto;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author akifova
 * 13.02.2021
 */
public interface IFileUtilService {
    void checkDirectoryExists(String directory);

    byte[] compress(byte[] data);

    byte[] decompress(byte[] data);

    String getRootFolderAddressForUploads();

    String createLocation(ModuleFolder moduleFolder, List<String> subDirectory);

    String createUrlAddress(String location);

    String getFileSeparator();

    byte[] convertToJpg(File file) throws IOException;

    byte[] encryptByteArray(byte[] inputFile);

    byte[] decryptByteArray(byte[] inputFile);

    ByteArrayOutputStream createZip(List<ZipFileDto> files, String zipFileName) throws IOException;
}
