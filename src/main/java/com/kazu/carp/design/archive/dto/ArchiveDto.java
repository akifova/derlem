package com.kazu.carp.design.archive.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.archive.CheckArchiveUserFiles;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.common.firm.dto.FirmDto;
import com.kazu.carp.design.plan.dto.DesignPlanDto;
import com.kazu.carp.security.account.dto.UserAccountDto;
import com.kazu.carp.specimen.creel.dto.CreelDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.*;
import com.kazu.carp.yarn.definition.dto.YarnDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author akifova
 * 08.06.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ArchiveDto extends BaseDto {
    @Schema(title = "Desenin Adı (Numune Öncesi)")
    @NotBlank(message = "{notBlank.design.archive.designName}")
    @Size(min = 1, max = 30, message = "'{design.archive.designName}' {general.field.size.min.max.error}")
    private String designName;

    @Schema(title = "Desenin Beğenilme Oranı")
    private short starRating;

    @Schema(title = "Desenin Zorluk Derecesi (0..100)")
    private int difficulty;

    @Schema(title = "Desen Versiyon Numarası")
    private short versionNumber;

    @Schema(title = "Desenin Son Versiyon Aldığı Tarih")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime versionDate;

    @Schema(title = "Arama Kelimeleri")
    private String tag;

    @Schema(title = "Desenin Durumu")
    private ArchiveStatus archiveStatus;

    @Schema(title = "Cağ Dizilim Formu", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.design.archive.creel}")
    private CreelDto creel;

    @Schema(title = "Koleksiyon Tanımı")
    private CollectionDto collection;

    @Schema(title = "Kalite Tanımı")
    private QualityDto quality;

    @Schema(title = "Palet Tanımı")
    private PaletteDto palette;

    @Schema(title = "Bordür Rengi")
    private PaletteLineToYarnDto bordure;

    @Schema(title = "Zemin Rengi")
    private PaletteLineToYarnDto floor;

    @Schema(title = "Desen Kodu", description = "Numuneye Dönüştürülme İşleminden İtibaren Zorunlu")
    @FieldNotEmpty(message = "{fieldNotEmpty.design.archive.design}", groups = OnUpdate.class)
    private DesignDto design;

    @Schema(title = "Ebat Tanımı", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.design.archive.size}")
    private SizeDto size;

    @Schema(title = "Şekil Tanımı", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.design.archive.shape}")
    private ShapeDto shape;

    @Schema(title = "Kenar Formu", description = "Numuneye Dönüştürülme İşleminden İtibaren Zorunlu")
    @FieldNotEmpty(message = "{fieldNotEmpty.design.archive.edge}", groups = OnUpdate.class)
    private EdgeDto edge;

    @Schema(title = "Overlok İpliği")
    private YarnDto overlockYarn;

    @Schema(title = "Yan Kenar Tanımı", description = "Numuneye Dönüştürülme İşleminden İtibaren Zorunlu")
    @FieldNotEmpty(message = "{fieldNotEmpty.design.archive.sideEdge}", groups = OnUpdate.class)
    private EdgeDto sideEdge;

    @Schema(title = "Dokuma Tipi", description = "Numuneye Dönüştürülme İşleminden İtibaren Zorunlu")
    @NotNull(message = "{fieldNotEmpty.design.archive.weaveType}", groups = OnUpdate.class)
    private WeaveType weaveType;

    @Schema(title = "Seyreltme Var mı?")
    @NotNull(message = "{notNull.design.archive.makingRare}")
    private boolean makingRare;

    @Schema(title = "Seyreltme Renkleri Palet Sıraları", example = "0- veya 0-16-45-",
            description = "Seyreltme Rengi olarak 0ncı palet kullanılır.\nBirden Fazla Seyreltme rengi olması durumunda tire işareti ile ayrılarak yazılmalıdır.")
    private String makingRarePaletteIndexes;

    @Schema(title = "Müşteri Tanımı")
    private FirmDto customer;

    @Schema(title = "Desen Plan Tanımı", required = true)
    @FieldNotEmpty(message = "{fieldNotEmpty.design.archive.designPlan}")
    private DesignPlanDto designPlan;

    @Schema(title = "Tasarımcı")
    private UserAccountDto designer;

    @Schema(hidden = true)
    @CheckArchiveUserFiles
    private List<ArchiveToUserFileDto> archiveToUserFiles;

    @Schema(hidden = true)
    private List<PixelRatioDto> pixelRatios;
}