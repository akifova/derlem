package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.yarn.definition.dto.YarnDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author akifova
 * 08.02.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PaletteLineToYarnDto extends BaseDto implements Serializable {

    @Schema(title = "Latte Oranı")
    private short lattePercentage;

    @Schema(hidden = true)
    private PaletteLineDto paletteLine;

    @Schema(title = "Kullanılan İplik")
    private YarnDto yarn;
}