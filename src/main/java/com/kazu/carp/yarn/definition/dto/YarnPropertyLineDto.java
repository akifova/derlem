package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
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
 * 04.05.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class YarnPropertyLineDto extends BaseDto implements Serializable {

    @Schema(title = "Özellik Adı")
    @NotBlank(message = "{notBlank.yarn.yarnPropertyLine.attribute}")
    @Size(min = 1, max = 40, message = "'{yarn.yarnPropertyLine.attribute}' {general.field.size.min.max.error}")
    private String attribute;

    @Schema(title = "Özellik Kodu")
    @NotBlank(message = "{notBlank.yarn.yarnPropertyLine.code}")
    @Size(min = 1, max = 10, message = "'{yarn.yarnPropertyLine.code}' {general.field.size.min.max.error}")
    private String code;

    @Schema(title = "Özellik Tanımı", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.yarn.yarnPropertyLine.yarnProperty}")
    private YarnPropertyDto yarnProperty;

    @Schema(hidden = true)
    private List<YarnToPropertyLineDto> yarnToPropertyLines;
}