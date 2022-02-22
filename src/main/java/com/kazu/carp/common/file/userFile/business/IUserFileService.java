package com.kazu.carp.common.file.userFile.business;

import com.kazu.carp.common.file.userFile.domain.UserFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author akifova
 * 16.02.2021
 */
public interface IUserFileService {
    UserFile createEntity(MultipartFile file, String location);
}
