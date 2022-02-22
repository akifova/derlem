package com.kazu.carp.design.plan.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.common.explanation.dto.ExplanationDto;
import com.kazu.carp.security.account.dto.UserAccountDto;
import com.kazu.carp.specimen.creel.dto.CreelDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DesignPlanDto extends BaseDto {

    @Schema(title = "Plan Başlığı")
    @NotBlank(message = "{notBlank.design.designPlan.title}")
    @Size(min = 1, max = 100, message = "'{design.designPlan.title}' {general.field.size.min.max.error}")
    private String title;

    @Schema(title = "Termin Tarihi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{notNull.design.designPlan.deadLine}")
    private LocalDate deadLine;

    @Schema(title = "Görevler", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.design.designPlan.task.text}")
    private ExplanationDto task;
    private UserAccountDto owner;
    private List<CreelDto> creels;
    private List<DesignPlanToUserFileDto> designPlanToFiles;
}