package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class QualityDto extends BaseDto {

    @Schema(title = "Tarak Sayısı")
    @NotNull(message = "{notNull.supplyChain.production.definition.stockCard.quality.crest}")
    @Min(value = 10, message = "{min.supplyChain.production.definition.stockCard.quality.crest}")
    @Max(value = 200, message = "{max.supplyChain.production.definition.stockCard.quality.crest}")
    private int crest;

    @Schema(title = "Atkı Sayısı")
    @NotNull(message = "{notNull.supplyChain.production.definition.stockCard.quality.woof}")
    @Min(value = 10, message = "{min.supplyChain.production.definition.stockCard.quality.woof}")
    @Max(value = 200, message = "{max.supplyChain.production.definition.stockCard.quality.woof}")
    private int woof;

    @Schema(title = "Kalite Kodu")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.quality.code}")
    @Size(min = 1, max = 5, message = "'{supplyChain.production.definition.stockCard.quality.code}' {general.field.size.min.max.error}")
    private String code;

    @Schema(title = "Kalite Tanımı")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.quality.code}")
    @Size(min = 1, max = 100, message = "'{supplyChain.production.definition.stockCard.quality.code}' {general.field.size.min.max.error}")
    private String definition;
}
