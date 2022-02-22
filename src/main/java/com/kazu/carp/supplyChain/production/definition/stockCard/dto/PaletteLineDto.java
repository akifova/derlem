package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.design.color.dto.ColorDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PaletteLineDto extends BaseDto {

    @Schema(title = "Palet Sıra No")
    @Min(value = 1, message = "{supplyChain.production.definition.stockCard.palette.paletteLine.rotationNumber.mustGreaterThenZero}")
    private int rotationNumber;

    @Schema(title = "RGB")
    private ColorDto color;

    @Schema(hidden = true)
    private PaletteDto palette;

    @Schema(title = "Palet Satırı Latte Oranları")
    private List<PaletteLineToYarnDto> lineToYarns;
}
