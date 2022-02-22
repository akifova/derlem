package com.kazu.carp.common.file.download.business;

import com.kazu.carp.common.file.util.business.IFileUtilService;
import com.kazu.carp.common.file.util.dto.ZipFileDto;
import com.kazu.carp.design.archive.business.IArchiveRdService;
import com.kazu.carp.design.archive.dto.ArchiveDto;
import com.kazu.carp.design.archive.dto.ArchiveFileType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author akifova
 * 15.08.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class DownloadServiceImpl implements IDownloadService {

    final IArchiveRdService archiveRdService;
    final IFileUtilService fileUtilService;

    public DownloadServiceImpl(IArchiveRdService archiveRdService, IFileUtilService fileUtilService) {
        this.archiveRdService = archiveRdService;
        this.fileUtilService = fileUtilService;
    }

    @Override
    public void bmpByArchiveId(String archiveId, boolean includeSupplementary, HttpServletResponse response) throws IOException {
        ArchiveDto archiveDto = archiveRdService.findOne(archiveId);
        var userFileDto = archiveDto.getArchiveToUserFiles().stream().filter(f->f.getFileType() == ArchiveFileType.original).map(m->m.getUserFile()).findFirst().get();
        String fileName = userFileDto.getOriginalFileName();

        if(includeSupplementary) {
            prepareResponse(response, FilenameUtils.removeExtension(fileName) + ".zip");
            List<ZipFileDto> zipFiles = createZipFile(archiveDto);
            putZipFileOnResponse(zipFiles, response, fileName);
        } else {
            prepareResponse(response, fileName);
            var originalFile = new File(userFileDto.getLocation() + fileUtilService.getFileSeparator() + userFileDto.getFileName());
            putFileOnResponse(FileUtils.readFileToByteArray(originalFile), response, false);
        }
    }

    @Override
    public void jpgByArchiveId(String archiveId, HttpServletResponse response, boolean convertBase64Format) throws IOException {
        ArchiveDto archiveDto = archiveRdService.findOne(archiveId);
        var userFileDto = archiveDto.getArchiveToUserFiles().stream().filter(f->f.getFileType() == ArchiveFileType.original).map(m->m.getUserFile()).findFirst().get();
        String fileName = FilenameUtils.removeExtension(userFileDto.getOriginalFileName()) + ".jpg";
        var originalFile = new File(userFileDto.getLocation() + fileUtilService.getFileSeparator() + userFileDto.getFileName());

        var jpgFile = fileUtilService.convertToJpg(originalFile);

        prepareResponse(response, fileName);
        putFileOnResponse(jpgFile, response, convertBase64Format);
    }

    private void prepareResponse(HttpServletResponse response, String fileName) {
        response.setHeader(HttpHeaders.PRAGMA, "public");
        response.setHeader(HttpHeaders.EXPIRES, "0");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "must - revalidate, post - check = 0, pre - check = 0");
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application - download");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        response.setHeader(HttpHeaders.CONTENT_ENCODING, "binary");
    }

    private void putFileOnResponse(byte[] fileData, HttpServletResponse response, boolean convertBase64Format) throws IOException {
        OutputStream outStream = response.getOutputStream();
        outStream.write(convertBase64Format ? Base64.getEncoder().encode(fileData) : fileData);
        outStream.close();
        response.flushBuffer();
    }

    private void putZipFileOnResponse(List<ZipFileDto> zipFiles, HttpServletResponse response, String fileName) throws IOException {
        ByteArrayOutputStream baos = fileUtilService.createZip(zipFiles, fileName);
        byte[] zipBytes = baos.toByteArray();
        OutputStream outStream = response.getOutputStream();
        outStream.write(zipBytes);
        outStream.close();
        response.flushBuffer();
    }

    private List<ZipFileDto> createZipFile(ArchiveDto archiveDto) throws FileNotFoundException {
        List<ZipFileDto> zipFiles = new ArrayList<>();
        var originalFileDto = archiveDto.getArchiveToUserFiles()
                .stream()
                .filter(f-> f.getFileType() == ArchiveFileType.original)
                .findFirst().orElse(null);

        if(originalFileDto == null) {
            throw new FileNotFoundException();
        }

        var originalFile = new File(originalFileDto.getUserFile().getLocation() + fileUtilService.getFileSeparator() + originalFileDto.getUserFile().getFileName());
        var original = ZipFileDto.builder().file(originalFile).fileName(originalFileDto.getUserFile().getOriginalFileName()).zipFolder("").build();

        zipFiles.add(original);

        var emptyFileDto = archiveDto.getArchiveToUserFiles()
                .stream()
                .filter(f-> f.getFileType() == ArchiveFileType.empty)
                .findFirst().orElse(null);

        var scanningFileDto = archiveDto.getArchiveToUserFiles()
                .stream()
                .filter(f-> f.getFileType() == ArchiveFileType.scanning)
                .findFirst().orElse(null);

        var emptyFile = new File(emptyFileDto.getUserFile().getLocation() + fileUtilService.getFileSeparator() + emptyFileDto.getUserFile().getFileName());
        var scanningFile = new File(scanningFileDto.getUserFile().getLocation() + fileUtilService.getFileSeparator() + scanningFileDto.getUserFile().getFileName());

        var empty = ZipFileDto.builder().file(emptyFile).fileName(emptyFileDto.getUserFile().getOriginalFileName()).zipFolder(emptyFileDto.getFileType().getValue()).build();
        var scanning = ZipFileDto.builder().file(scanningFile).fileName(scanningFileDto.getUserFile().getOriginalFileName()).zipFolder(scanningFileDto.getFileType().getValue()).build();

        zipFiles.add(empty);
        zipFiles.add(scanning);

        return zipFiles;
    }
}