package com.kazu.carp.app.validation.archive;

import com.kazu.carp.design.archive.dto.ArchiveFileType;
import com.kazu.carp.design.archive.dto.ArchiveToUserFileDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author akifova
 * 24.09.2021
 */
public class CheckArchiveUserFilesValidator implements ConstraintValidator<CheckArchiveUserFiles, List<ArchiveToUserFileDto>> {

    @Override
    public boolean isValid(List<ArchiveToUserFileDto> archiveToUserFiles, ConstraintValidatorContext constraintValidatorContext) {
        if(archiveToUserFiles == null) {
            return false;
        }

        if(archiveToUserFiles.size() != 3) {
            return false;
        }

        var original = archiveToUserFiles.stream()
                .filter(f-> f.getFileType() == ArchiveFileType.original).findFirst().orElse(null);

        var empty = archiveToUserFiles.stream()
                .filter(f-> f.getFileType() == ArchiveFileType.empty).findFirst().orElse(null);

        var scanning = archiveToUserFiles.stream()
                .filter(f-> f.getFileType() == ArchiveFileType.scanning).findFirst().orElse(null);

        return original != null && empty != null && scanning != null;
    }
}
