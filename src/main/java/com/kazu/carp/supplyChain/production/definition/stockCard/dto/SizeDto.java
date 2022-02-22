package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SizeDto extends BaseDto {

    @Schema(title = "Ebat Tipi", accessMode = Schema.AccessMode.READ_ONLY)
    private SizeType sizeType;

    @Schema(title = "Ebat Kodu")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.size.code}")
    @Size(min = 1, max = 4, message = "'{supplyChain.production.definition.stockCard.size.code}' {general.field.size.min.max.error}")
    private String code;

    @Schema(title = "Ebat Tanımı")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.size.definition}")
    @Size(min = 1, max = 100, message = "'{supplyChain.production.definition.stockCard.size.definition}' {general.field.size.min.max.error}")
    private String definition;

    @Schema(title = "En (cm)")
    @NotNull(message = "{notNull.supplyChain.production.definition.stockCard.size.width}")
    @Min(value = 20, message = "{min.supplyChain.production.definition.stockCard.size.width}")
    @Max(value = 500, message = "{min.supplyChain.production.definition.stockCard.size.width}")
    private int width;

    @Schema(title = "Boy (cm)")
    @NotNull(message = "{notNull.supplyChain.production.definition.stockCard.size.length}")
    @Min(value = 20, message = "{min.supplyChain.production.definition.stockCard.size.length}")
    private int length;

    @Schema(title = "m²", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal sqm;

    @Schema(title = "inch", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal inch;
}
