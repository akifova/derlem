package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author akifova
 * 01.05.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class YarnTypeDto extends BaseDto implements Serializable {
    @Schema(title = "İplik Tipi")
    @NotBlank(message = "{notBlank.yarn.yarnType.name}")
    @Size(min = 1, max = 30, message = "'{yarn.yarnType.name}' {general.field.size.min.max.error}")
    private String name;

    @Schema(hidden = true)
    private List<YarnGroupDto> yarnGroups;
}