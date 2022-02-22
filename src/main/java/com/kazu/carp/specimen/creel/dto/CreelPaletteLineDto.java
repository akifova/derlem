package com.kazu.carp.specimen.creel.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.design.color.dto.ColorDto;
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
public class CreelPaletteLineDto extends BaseDto implements Serializable {
    @Schema(title = "Palet Satır Numarası")
    private int rotationNumber;

    @Schema(title = "RGB")
    private ColorDto color;

    @Schema(title = "CDF Palet Tanımı")
    private CreelPaletteDto creelPalette;

    @Schema(hidden = true)
    private List<CreelPaletteLineToYarnDto> lineToYarns;
}