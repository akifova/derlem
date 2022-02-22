package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class EdgeDto extends BaseDto {

    @Schema(title = "Kenar Formu Tanımı")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.size.definition}")
    @Size(min = 1, max = 100, message = "'{supplyChain.production.definition.stockCard.size.definition}' {general.field.size.min.max.error}")
    private String definition;

    @Schema(title = "Kenar Formu Kodu")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.size.code}")
    @Size(min = 1, max = 5, message = "'{supplyChain.production.definition.stockCard.size.code}' {general.field.size.min.max.error}")
    private String code;

    @Schema(title = "Kenar Boşluğu (mm)")
    @NotNull(message = "{notNull.supplyChain.production.definition.stockCard.edge.length}")
    @Positive(message = "{positiveOrZero.supplyChain.production.definition.stockCard.edge.length}")
    private int length;
}