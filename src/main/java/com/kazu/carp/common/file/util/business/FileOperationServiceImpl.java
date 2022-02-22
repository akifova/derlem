package com.kazu.carp.common.file.util.business;

import com.kazu.carp.image.business.IImageUtilService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author akifova
 * 16.02.2021
 */
@Service
public class FileOperationServiceImpl implements IFileOperationService {
    private static final Logger logger = LoggerFactory.getLogger(FileOperationServiceImpl.class);

    final IFileUtilService fileUtilService;
    final IImageUtilService imageUtilService;

    public FileOperationServiceImpl(IFileUtilService fileUtilService, IImageUtilService imageUtilService) {
        this.fileUtilService = fileUtilService;
        this.imageUtilService = imageUtilService;
    }

    @Override
    public void saveImageFileOnDisk(byte[] file, String fileAddress, String fileName) {
        saveThumbnailOnDisk(file, fileAddress, fileName);
        saveFileOnDisk(file, fileAddress, fileName);
    }

    @Override
    public void deleteFile(String location, String fileName) {
        fileUtilService.checkDirectoryExists(location);
        if(!location.endsWith(fileUtilService.getFileSeparator())) {location += fileUtilService.getFileSeparator();}
        File f = new File(location + fileName);

        if(f != null) {
            f.delete();
        }
    }

    private void saveFileOnDisk(byte[] fileData, String fileAddress, String fileName) {
        fileUtilService.checkDirectoryExists(fileAddress);
        try {
            FileUtils.writeByteArrayToFile(new File(fileAddress + fileName), fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveThumbnailOnDisk(byte[] file, String fileAddress, String fileName) {
        String address = fileAddress.replaceFirst("originals", "thumbnails");
        fileUtilService.checkDirectoryExists(address);
        try {
            BufferedImage thumb = imageUtilService.resize(file, 80);
            String fileNameWithOutExtension = FilenameUtils.removeExtension(fileName);
            ImageIO.write(thumb, "png", new File(address + fileNameWithOutExtension + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}