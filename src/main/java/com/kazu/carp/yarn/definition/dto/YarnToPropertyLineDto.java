package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author akifova
 * 04.05.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class YarnToPropertyLineDto extends BaseDto implements Serializable {

    @Schema(title = "Özellik Satır Tanımı", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.yarn.yarnToPropertyLine.yarnPropertyLine}")
    private YarnPropertyLineDto yarnPropertyLine;

    @Schema(title = "İplik Tanımı", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.yarn.yarnToPropertyLine.yarn}")
    private YarnDto yarn;
}