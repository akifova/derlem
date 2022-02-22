package com.kazu.carp.design.archive.dto;

import lombok.*;

/**
 * @author akifova
 * 10.11.2021
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class SaveArchiveFileDto {
    private byte[] file;
    private String location;
    private String fileName;
}
