package com.kazu.carp.common.file.userFile.persistence;

import com.kazu.carp.common.file.userFile.domain.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author akifova
 * 16.02.2021
 */
public interface IUserFileDao extends JpaRepository<UserFile, String> {
}
