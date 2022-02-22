package com.kazu.carp.design.archive.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.common.file.userFile.dto.UserFileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author akifova
 * 08.06.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ArchiveToUserFileDto extends BaseDto {
    private short versionNumber;
    private LocalDateTime versionDate;
    private ArchiveDto archive;
    private UserFileDto userFile;
    private ArchiveFileType fileType;
}