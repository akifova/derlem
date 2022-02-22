package com.kazu.carp.common.file.userFile.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.design.plan.dto.DesignPlanToUserFileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserFileDto extends BaseDto {

    private String fileName;
    private String originalFileName;
    private String location;
    private String url;
    private String fileType;
    private long fileSize;
    private List<DesignPlanToUserFileDto> designPlanToFiles;
}