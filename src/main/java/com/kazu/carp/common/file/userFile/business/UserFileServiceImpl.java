package com.kazu.carp.common.file.userFile.business;

import com.kazu.carp.common.file.userFile.domain.UserFile;
import com.kazu.carp.common.file.userFile.persistence.IUserFileDao;
import com.kazu.carp.common.file.util.business.IFileUtilService;
import com.kazu.carp.common.util.business.IComplementaryService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author akifova
 * 16.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class UserFileServiceImpl implements IUserFileService {
    private static final Logger logger = LoggerFactory.getLogger(UserFileServiceImpl.class);

    final IUserFileDao dao;
    final IComplementaryService complementaryService;
    final IFileUtilService fileUtilService;

    public UserFileServiceImpl(IUserFileDao dao, IComplementaryService complementaryService, IFileUtilService fileUtilService) {
        this.dao = dao;
        this.complementaryService = complementaryService;
        this.fileUtilService = fileUtilService;
    }

    @Override
    public UserFile createEntity(MultipartFile file, String location) {
        var userFile = UserFile.builder()
                .fileName(complementaryService.createUniqueFromMillis() + "." + FilenameUtils.getExtension(file.getOriginalFilename()))
                .originalFileName(file.getOriginalFilename())
                .location(location)
                .url(fileUtilService.createUrlAddress(location))
                .fileType(file.getContentType())
                .fileSize(file.getSize()).build();

        /*try {
            userFile.setFileData(UserFileData.builder().fileData(file.getBytes()).file(userFile).build());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return userFile;
    }
}