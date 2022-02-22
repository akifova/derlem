package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class YarnPropertyDto extends BaseDto implements Serializable {

    @Schema(title = "Özellik Tanımı")
    @NotBlank(message = "{notBlank.yarn.yarnProperty.name}")
    @Size(min = 1, max = 30, message = "'{yarn.yarnProperty.name}' {general.field.size.min.max.error}")
    private String name;

    @Schema(title = "Özellik Tipi")
    @NotNull(message = "notNull.yarn.yarnProperty.propertyType")
    @ValueOfEnum(enumClass = YarnPropertyType.class, message = "{valueOfEnum.yarn.yarnProperty.propertyType}")
    private YarnPropertyType yarnPropertyType;

    @Schema(title = "Sevk Barkodunda Basılacak mı?", hidden = true)
    private short printOnBarcode;

    @Schema(hidden = true)
    private List<YarnGroupToPropertyDto> yarnGroupToProperties;

    @Schema(hidden = true)
    private List<YarnPropertyLineDto> yarnPropertyLines;

}