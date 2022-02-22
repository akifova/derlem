package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PaletteDto extends BaseDto {

    @Schema(title = "Palet Kodu")
    @NotEmpty(message = "{supplyChain.production.definition.stockCard.palette.code.isEmpty}")
    @Size(min = 1, max = 3, message = "{general.field.size.min.max.error}")
    private String code;

    @Schema(title = "Palet Tanımı")
    @NotEmpty(message = "{supplyChain.production.definition.stockCard.palette.code.isEmpty}")
    @Size(min = 2, max = 100, message = "{general.field.size.min.max.error}")
    private String definition;

    @Schema(title = "Palet Satırları")
    private List<PaletteLineDto> paletteLines;
}
