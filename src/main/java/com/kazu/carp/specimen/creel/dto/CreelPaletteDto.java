package com.kazu.carp.specimen.creel.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class CreelPaletteDto extends BaseDto implements Serializable {

    @Schema(title = "Palet Versiyon Numarası")
    private short versionNumber;

    @Schema(title = "Cağ Dizilim Formu Tanımı")
    private CreelDto creel;

    @Schema(hidden = true)
    private List<CreelPaletteLineDto> creelPaletteLines;
}