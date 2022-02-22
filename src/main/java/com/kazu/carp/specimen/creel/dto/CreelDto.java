package com.kazu.carp.specimen.creel.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.design.archive.dto.ArchiveDto;
import com.kazu.carp.design.plan.dto.DesignPlanDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author akifova
 * 04.05.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CreelDto extends BaseDto implements Serializable {
    @Schema(title = "Aktif Versiyon Numarası")
    private String activeVersion;

    @Schema(title = "Palet Kodu")
    @NotBlank(message = "{notBlank.specimen.creel.paletteCode}")
    @Size(min = 1, max = 20, message = "'{specimen.creel.paletteCode}' {general.field.size.min.max.error}")
    private String paletteCode;

    @Schema(title = "Kalite Adı")
    @NotBlank(message = "{notBlank.specimen.creel.qualityName}")
    @Size(min = 1, max = 20, message = "'{specimen.creel.qualityName}' {general.field.size.min.max.error}")
    private String qualityName;

    @Schema(title = "Numune Adı")
    @NotBlank(message = "{notBlank.specimen.creel.sampleName}")
    @Size(min = 1, max = 50, message = "'{specimen.creel.sampleName}' {general.field.size.min.max.error}")
    private String sampleName;

    @Schema(title = "Şardon Var mı?")
    private Raising raising;

    @Schema(title = "Yıkama Var mı?")
    private Washing washing;

    @Schema(title = "Desen Plan Tanımı", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.specimen.creel.designPlan}")
    private DesignPlanDto designPlan;

    @Schema(title = "CDF Kalite Tanımı")
    @Valid
    private CreelQualityDto creelQuality;

    @Schema(title = "CDF Palet Tanımı")
    @Valid
    private CreelPaletteDto creelPalette;

    @Schema(title = "Açıklama")
    private List<CreelToExplanationDto> explanations;

    @Schema(hidden = true)
    private List<ArchiveDto> archives;
}