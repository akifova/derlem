package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ShapeDto extends BaseDto implements Serializable {

    @Schema(title = "Şekil Kodu")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.shape.code}")
    @Size(min = 1, max = 5, message = "'{supplyChain.production.definition.stockCard.shape.code}' {general.field.size.min.max.error}")
    private String code;

    @Schema(title = "Şekil Tanımı")
    @NotBlank(message = "{notBlank.supplyChain.production.definition.stockCard.shape.definition}")
    @Size(min = 1, max = 100, message = "'{supplyChain.production.definition.stockCard.shape.definition}' {general.field.size.min.max.error}")
    private String definition;
}
