package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DesignDto extends BaseDto {

    @Schema(title = "Desen Kodu")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.design.code}")
    @Size(min = 1, max = 4, message = "'{supplyChain.production.definition.stockCard.design.code}' {general.field.size.min.max.error}")
    private String code;
}
