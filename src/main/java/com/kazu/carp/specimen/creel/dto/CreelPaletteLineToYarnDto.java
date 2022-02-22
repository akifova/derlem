package com.kazu.carp.specimen.creel.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.specimen.yarn.dto.SampleYarnDto;
import com.kazu.carp.yarn.definition.dto.YarnDto;
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
public class CreelPaletteLineToYarnDto extends BaseDto implements Serializable {

    @Schema(title = "Latte Oranı")
    private short lattePercentage;

    @Schema(title = "CDF Palet Satır Tanımı")
    private CreelPaletteLineDto creelPaletteLine;

    @Schema(title = "İplik Tanımı")
    private YarnDto yarn;

    @Schema(title = "Numune İplik Tanımı")
    private SampleYarnDto sampleYarn;
}