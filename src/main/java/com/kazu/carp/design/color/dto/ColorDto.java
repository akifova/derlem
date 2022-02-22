package com.kazu.carp.design.color.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.specimen.creel.dto.CreelPaletteLineDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author akifova
 * 08.02.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ColorDto extends BaseDto implements Serializable {

    @Schema(title = "Renk Kodu")
    private String code;

    @Schema(title = "Renk Açıklaması")
    private String definition;

    @Schema(title = "RGB Red Değeri")
    private Integer rgbRed;

    @Schema(title = "RGB Green Değeri")
    private Integer rgbGreen;

    @Schema(title = "RGB Blue Değeri")
    private Integer rgbBlue;

    @Schema(hidden = true)
    private List<PaletteLineDto> lines;

    @Schema(hidden = true)
    private List<CreelPaletteLineDto> creelPaletteLines;
}