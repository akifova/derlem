package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.jpa.BaseDto;
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
public class YarnGroupToPropertyDto extends BaseDto implements Serializable {
    @Schema(title = "Özellik Tanımı")
    private YarnPropertyDto yarnProperty;

    @Schema(title = "İplik Grup Tanımı")
    private YarnGroupDto yarnGroup;
}