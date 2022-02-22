package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.common.brand.dto.BrandDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CollectionDto extends BaseDto {

    @Schema(title = "Koleksiyon Kodu")
    @NotEmpty(message = "{supplyChain.production.definition.stockCard.collection.code.isEmpty}")
    @Size(min = 2, max = 10, message = "{general.field.size.min.max.error}")
    private String code;

    @Schema(title = "Koleksiyon Tanımı")
    @NotEmpty(message = "{supplyChain.production.definition.stockCard.collection.definition.isEmpty}")
    @Size(min = 2, max = 100, message = "{general.field.size.min.max.error}")
    private String definition;

    @Schema(title = "Çekme Payı")
    @PositiveOrZero(message = "{positiveOrZero.production.definition.stockCard.collection.definition.shrinkage}")
    @NotNull(message = "{notNull.production.definition.stockCard.collection.definition.shrinkage}")
    private int shrinkage;

    @Schema(title = "Boyanacak mı?")
    @NotNull(message = "{notNull.production.definition.stockCard.collection.definition.painted}")
    private boolean painted;

    @Schema(title = "Yıkanacak mı?")
    @NotNull(message = "{notNull.production.definition.stockCard.collection.definition.washed}")
    private boolean washed;

    @Schema(title = "Yatay Dokuma Yapılacak mı?")
    @NotNull(message = "{notNull.production.definition.stockCard.collection.definition.horizontalWeaving}")
    private boolean horizontalWeaving;

    @Schema(title = "Koleksiyonun Bağlı Olduğu Marka", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.production.definition.stockCard.collection.brand}")
    private BrandDto brand;
}
