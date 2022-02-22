package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.specimen.yarn.dto.SampleYarnDto;
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
public class YarnGroupDto extends BaseDto implements Serializable {

    @Schema(title = "İplik Grubu")
    @NotBlank(message = "{notBlank.yarn.yarnGroup.name}")
    @Size(min = 1, max = 30, message = "'{yarn.yarnGroup.name}' {general.field.size.min.max.error}")
    private String name;

    @Schema(title = "İplik Tipi", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.yarn.yarnGroup.yarnType}")
    private YarnTypeDto yarnType;

    @Schema(title = "Grubun Kullanacağı Özellikler")
    private List<YarnGroupToPropertyDto> properties;

    @Schema(hidden = true)
    private List<SampleYarnDto> sampleYarns;

    @Schema(hidden = true)
    private List<YarnDto> yarns;
}