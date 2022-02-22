package com.kazu.carp.common.explanation.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.design.plan.dto.DesignPlanDto;
import com.kazu.carp.security.account.dto.UserAccountDto;
import com.kazu.carp.specimen.creel.dto.CreelToExplanationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ExplanationDto extends BaseDto {

    @Schema(title = "Açıklama Metni")
    @NotBlank(message = "{notBlank.common.explanation.text}")
    @Size(min = 1, max = 8000, message = "'{common.explanation.text}' {general.field.size.min.max.error}")
    private String text;

    @Schema(hidden = true)
    private String backColor;

    @Schema(hidden = true)
    private short likeCount;

    @Schema(hidden = true)
    private UserAccountDto writer;

    @Schema(hidden = true)
    private ExplanationDto parentNote;

    @Schema(hidden = true)
    private List<ExplanationDto> comments;

    @Schema(hidden = true)
    private List<DesignPlanDto> designPlans;

    @Schema(hidden = true)
    private List<CreelToExplanationDto> creelExplanations;
}