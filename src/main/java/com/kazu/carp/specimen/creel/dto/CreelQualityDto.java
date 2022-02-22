package com.kazu.carp.specimen.creel.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.yarn.definition.dto.YarnDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author akifova
 * 04.05.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CreelQualityDto extends BaseDto implements Serializable {
    @Schema(title = "Versiyon Numarası")
    private short versionNumber;

    @Schema(title = "Atkı Sayısı")
    @NotNull(message = "{notNull.specimen.creel.creelQuality.woof}")
    @Min(value = 10, message = "{min.specimen.creel.creelQuality.woof}")
    @Max(value = 200, message = "{min.specimen.creel.creelQuality.woof}")
    private int woof;

    @Schema(title = "Tarak Sayısı")
    @NotNull(message = "{notNull.specimen.creel.creelQuality.crest}")
    @Min(value = 10, message = "{min.specimen.creel.creelQuality.crest}")
    @Max(value = 200, message = "{min.specimen.creel.creelQuality.crest}")
    private int crest;

    @Schema(title = "Lanset")
    @NotNull(message = "{notNull.specimen.creel.creelQuality.lancet}")
    @Min(value = 5, message = "{min.specimen.creel.creelQuality.lancet}")
    @Max(value = 50, message = "{min.specimen.creel.creelQuality.lancet}")
    private int lancet;

    @Schema(title = "Atkı İpi (Alt)")
    private YarnDto bottomWoofYarn;

    @Schema(title = "Atkı İpi (Orta)")
    private YarnDto middleWoofYarn;

    @Schema(title = "Atkı İpi (Üst)")
    private YarnDto topWoofYarn;

    @Schema(title = "Örgü Yapısı")
    @NotBlank(message = "{notBlank.specimen.creel.creelQuality.knittingType}")
    @Size(min = 1, max = 20, message = "'{specimen.creel.creelQuality.knittingType}' {general.field.size.min.max.error}")
    private String knittingType;

    @Schema(title = "Cağ Dizilim Formu Tanımı")
    private CreelDto creel;
}