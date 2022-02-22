package com.kazu.carp.specimen.creel.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.common.explanation.dto.ExplanationDto;
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
public class CreelToExplanationDto extends BaseDto implements Serializable {

    @Schema(title = "Cağ Dizilim Formu Tanımı")
    private CreelDto creel;

    @Schema(title = "Açıklama Tanımı")
    private ExplanationDto explanation;
}