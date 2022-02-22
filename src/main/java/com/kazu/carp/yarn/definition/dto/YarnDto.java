package com.kazu.carp.yarn.definition.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.app.validation.common.ValueOfEnum;
import com.kazu.carp.common.uom.dto.UnitOfMeasureDto;
import com.kazu.carp.specimen.creel.dto.CreelPaletteLineToYarnDto;
import com.kazu.carp.specimen.creel.dto.CreelQualityDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineToYarnDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author akifova
 * 04.05.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class YarnDto extends BaseDto implements Serializable {
    @Schema(title = "İplik Adı")
    @NotBlank(message = "{notBlank.yarn.name}")
    @Size(min = 1, max = 50, message = "'{yarn.name}' {general.field.size.min.max.error}")
    private String name;

    @Schema(title = "İplik Ağırlığı")
    @NotBlank(message = "{notBlank.yarn.weight}")
    @Size(min = 1, max = 20, message = "'{yarn.weight}' {general.field.size.min.max.error}")
    private String weight;

    @Schema(title = "İplik Hareket Tipi")
    @NotNull(message = "{valueOfEnum.yarn.actionType}")
    @ValueOfEnum(enumClass = YarnActionType.class, message = "{valueOfEnum.yarn.actionType}")
    private YarnActionType actionType;

    @Schema(title = "Minimum Stok Miktarı")
    @NotNull(message = "{notNull.yarn.minStock}")
    @PositiveOrZero(message = "{positiveOrZero.yarn.minStock}")
    private BigDecimal minStock;

    @Schema(title = "İplik Satılabilir mi?")
    private short salable;

    @Schema(title = "Mal Kabulde Kalite Kontrol Onayı Gerekli mi?")
    private short needQcApprove;

    @Schema(title = "Stok Takibi Yapılacak mı?")
    private short surveillance;

    @Schema(title = "Satınalma ve Takip Birimi (Ör:kg)")
    private UnitOfMeasureDto unitOfMeasure;

    @Schema(title = "Ağırlık Ölçü Birimi (ör: Dtex, Ne...)", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.yarn.unitOfMeasure}")
    private UnitOfMeasureDto weightUnit;

    @Schema(title = "İplik Grubu", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.yarn.yarnGroup}")
    private YarnGroupDto yarnGroup;

    @Schema(title = "Kullanım Durumu")
    @NotNull(message = "{valueOfEnum.yarn.usageType}")
    @ValueOfEnum(enumClass = YarnUsageType.class, message = "{valueOfEnum.yarn.usageType}")
    private YarnUsageType usageType;

    @Schema(title = "Kullanıcı Hatırlatma Etiketi")
    private String mark;

    @Schema(title = "İplik Özellikleri")
    private List<YarnToPropertyLineDto> yarnToPropertyLines;

    @Schema(hidden = true)
    private List<PaletteLineToYarnDto> paletteLineToYarns;

    @Schema(hidden = true)
    private List<CreelPaletteLineToYarnDto> creelPaletteLineToYarns;

    @Schema(hidden = true)
    private List<CreelQualityDto> bottomWoofYarn;

    @Schema(hidden = true)
    private List<CreelQualityDto> middleWoofYarn;

    @Schema(hidden = true)
    private List<CreelQualityDto> topWoofYarn;
}