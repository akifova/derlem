package com.kazu.carp.common.brand.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.CollectionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author akifova
 * 06.02.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class BrandDto extends BaseDto implements Serializable {

    @Schema(title = "Marka Tanımı")
    @NotBlank(message = "{notBlank.common.brand.name}")
    @Size(min = 2, max = 50, message = "{common.brand.name} {general.field.size.min.max.error}")
    private String name;

    @Schema(title = "Marka Kodu")
    @NotBlank(message = "{notBlank.common.brand.code}")
    @Size(min = 1, max = 3, message = "'{common.brand.code}' {general.field.size.min.max.error}")
    private String code;

    @Schema(hidden = true)
    private List<CollectionDto> collections;
}