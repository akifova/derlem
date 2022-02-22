package com.kazu.carp.design.plan.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.common.file.userFile.dto.UserFileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesignPlanToUserFileDto extends BaseDto {

    private UserFileDto userFile;
    private DesignPlanDto designPlan;
}