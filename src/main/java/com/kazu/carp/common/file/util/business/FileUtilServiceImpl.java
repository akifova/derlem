package com.kazu.carp.common.file.util.business;

import com.kazu.carp.common.file.util.dto.FileConstants;
import com.kazu.carp.common.file.util.dto.ModuleFolder;
import com.kazu.carp.common.file.util.dto.ZipFileDto;
import com.kazu.carp.common.util.business.IComplementaryService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.*;

/**
 * @author akifova
 * 13.02.2021
 */
@Service
public class FileUtilServiceImpl  implements  IFileUtilService {
    private static final Logger logger = LoggerFactory.getLogger(FileUtilServiceImpl.class);

    final Environment environment;
    final IComplementaryService complementaryService;

    public FileUtilServiceImpl(Environment environment, IComplementaryService complementaryService) {
        this.environment = environment;
        this.complementaryService = complementaryService;
    }

    @Override
    public void checkDirectoryExists(String directory) {
        String lastCharacter = directory.substring(directory.length()-1);

        if(!getFileSeparator().equals(lastCharacter)) {
            directory += getFileSeparator();
        }

        File f = new File(directory);
        if(!f.exists()) {
            f.mkdir();
        }
    }

    @Override
    public byte[] compress(byte[] data) {
        try {
            Deflater deflater = new Deflater();
            deflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            deflater.finish();
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer); // returns the generated code... index
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            byte[] output = outputStream.toByteArray();
            return output;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //LOG.debug("Original: " + data.length / 1024 + " Kb");
        //LOG.debug("Compressed: " + output.length / 1024 + " Kb");
    }

    @Override
    public byte[] decompress(byte[] data) {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            byte[] output = outputStream.toByteArray();
            //LOG.debug("Original: " + data.length);
            //LOG.debug("Compressed: " + output.length);
            return output;
        } catch (IOException e) {
            return null;
        } catch (DataFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getRootFolderAddressForUploads() {
        var rootFolder = getPropertyValueByOS("derlem.server.file.path.");
        if(!rootFolder.endsWith(getFileSeparator())) {
            rootFolder += getFileSeparator();
        }

        return rootFolder;
    }

    @Override
    public String createLocation(ModuleFolder moduleFolder, List<String> subDirectories) {
        StringBuilder address = new StringBuilder(getRootFolderAddressForUploads())
                .append("originals")
                .append(getFileSeparator())
                .append(moduleFolder.getValue())
                .append(getFileSeparator());
        checkDirectoryExists(address.toString());
        checkDirectoryExists(address.toString().replaceFirst("originals", "thumbnails"));

        subDirectories.stream().forEach(d -> {
            address.append(d).append(getFileSeparator());
            checkDirectoryExists(address.toString());
            checkDirectoryExists(address.toString().replaceFirst("originals", "thumbnails"));
        });

        return address.toString();
    }

    @Override
    public String createUrlAddress(String location) {
        return "/uploads/" + location
                .replace(getRootFolderAddressForUploads(), "")
                .replace("originals/", "")
                .replace("\\", "/");
    }

    @Override
    public String getFileSeparator() {
        return FileConstants.FILE_SEPARATOR;
    }

    @Override
    public byte[] convertToJpg(File file) throws IOException {
        BufferedImage bmp = ImageIO.read(file);
        ByteArrayOutputStream pngStream = new ByteArrayOutputStream();
        ImageIO.write(bmp, "JPG", pngStream);

        return pngStream.toByteArray();
    }

    private String getPropertyValueByOS(String propertyBase) {
        var OS = complementaryService.fixedTurkishCharacter((System.getProperty("os.name")).toUpperCase());
        System.out.println("*********************  OS *****************************");
        System.out.println(OS);
        System.out.println("*******************************************************");

        var resourcePath = "";
        switch (OS) {
            case "LINUX":
                resourcePath = environment.getProperty(propertyBase + "linux");
                break;
            case "WIN":
            case "WINDOWS 10":
                resourcePath = environment.getProperty(propertyBase + "win");
                break;
            default:
                resourcePath = environment.getProperty(propertyBase + "osx");
                break;
        }

        var devProfileSeparator = Arrays.stream(environment.getActiveProfiles()).anyMatch(p-> p.equals("prod")) ?
                "" : getFileSeparator() + "_" + environment.getProperty("spring.profiles.active");

        System.out.println("*****************  Resource Dir ***********************");
        System.out.println(resourcePath + devProfileSeparator + getFileSeparator());
        System.out.println("*******************************************************");

        return resourcePath + devProfileSeparator + getFileSeparator();
    }

    @Override
    public byte[] encryptByteArray(byte[] inputFile) {
        return doCrypto(Cipher.ENCRYPT_MODE, inputFile);
    }

    @Override
    public byte[] decryptByteArray(byte[] inputFile) {
        return doCrypto(Cipher.DECRYPT_MODE, inputFile);
    }

    @Override
    public ByteArrayOutputStream createZip(List<ZipFileDto> files, String zipFileName) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        var zipOutputStream = new ZipOutputStream(baos);

        files.stream().forEach(f->{
            try {
                var folder = "".equals(f.getZipFolder()) ? "" : f.getZipFolder() + "/";
                ZipEntry zipEntry = new ZipEntry(folder + f.getFileName());
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fs  = new FileInputStream(f.getFile());
                IOUtils.copy(fs, zipOutputStream);
                zipOutputStream.closeEntry();
                fs.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        zipOutputStream.close();

        return baos;
    }

    private byte[] doCrypto(int cipherMode, byte[] input) {
        try {
            Key secretKey = new SecretKeySpec(FileConstants.ENCRYPT_KEY.getBytes(), FileConstants.ALGORITHM);
            Cipher cipher = Cipher.getInstance(FileConstants.TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            byte[] outputBytes = cipher.doFinal(input);

            return outputBytes;

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException ex) {

        }
        return null;
    }
}
