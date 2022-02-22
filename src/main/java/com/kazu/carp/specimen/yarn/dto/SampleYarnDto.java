package com.kazu.carp.specimen.yarn.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.common.firm.dto.FirmDto;
import com.kazu.carp.common.uom.dto.UnitOfMeasureDto;
import com.kazu.carp.yarn.definition.dto.YarnGroupDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SampleYarnDto extends BaseDto {

    @Schema(title = "İplik Kodu")
    private String code;

    @Schema(title = "İplik Ağırlığı")
    @NotBlank(message = "{notBlank.specimen.yarn.weight}")
    @Size(min = 1, max = 20, message = "'{specimen.yarn.weight}' {general.field.size.min.max.error}")
    private String weight;

    @Schema(hidden = true)
    private int versionNumber;

    @Schema(title = "Tedarikçi Kodu")
    @NotBlank(message = "{notBlank.specimen.yarn.manufacturerCode}")
    @Size(min = 1, max = 30, message = "'{specimen.yarn.manufacturerCode}' {general.field.size.min.max.error}")
    private String manufacturerCode;

    @Schema(title = "Renk Kodu")
    private String colorCode;

    @Schema(title = "Renk Adı")
    private String colorName;

    @Schema(title = "İplik Numarası")
    private String yarnNumber;

    @Schema(title = "Büküm Sayısı")
    private int spin;

    @Schema(title = "Cips Oranı")
    private BigDecimal cipsPercentage;

    @Schema(title = "UV")
    private SampleYarnUV uvType;

    @Schema(title = "Fikse Tipi")
    private String fixedType;

    @Schema(title = "Elyaf Tipi")
    private String fiberType;

    @Schema(title = "Elyaf İnceliği")
    private String fiberFineness;

    @Schema(title = "Flament Sayısı")
    private String filamentQuantity;

    @Schema(title = "Tedarikçi")
    private FirmDto supplier;

    @Schema(title = "Ağırlık Birimi", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.specimen.yarn.weightUnit}")
    private UnitOfMeasureDto weightUnit;

    @Schema(hidden = true)
    private SampleYarnDto mainSampleYarn;

    @Schema(title = "İplik grubu", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.specimen.yarn.yarnGroup}")
    private YarnGroupDto yarnGroup;

    @Schema(hidden = true)
    private List<SampleYarnDto> sampleYarnVersions;
}
