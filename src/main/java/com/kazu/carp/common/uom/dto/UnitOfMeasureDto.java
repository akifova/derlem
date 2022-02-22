package com.kazu.carp.common.uom.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.yarn.definition.dto.YarnDto;
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
 * 29.04.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UnitOfMeasureDto extends BaseDto implements Serializable {
    @Schema(title = "Ölçü Birimi")
    @NotBlank(message = "{notBlank.common.uom.unit}")
    @Size(min = 1, max = 20, message = "'{common.uom.unit}' {general.field.size.min.max.error}")
    private String unit;

    @Schema(title = "Ölçü Birimi Kısaltması")
    @NotBlank(message = "{notBlank.common.uom.shortName}")
    @Size(min = 1, max = 10, message = "'{common.uom.shortName}' {general.field.size.min.max.error}")
    private String shortName;

    @Schema(hidden = true)
    private List<YarnDto> yarns;
}