package com.kazu.carp.common.file.util.dto;

import lombok.*;

import java.io.File;

/**
 * @author akifova
 * 15.08.2021
 */
@Getter @Setter @NoArgsConstructor @Builder @AllArgsConstructor
public class ZipFileDto {
    private File file;
    private String fileName;
    private String zipFolder;
}
