package com.kazu.carp.app.license.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author akifova
 * 05.10.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class LicenseDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 8426110490108339844L;

    @Schema(title = "Lisans Bitiş Tarihi")
    private LocalDate endDate;

    @Schema(title = "Server Mac Adresi")
    private String macAddress;

    @Schema(title = "Server CPU ID")
    private String processorId;

    @Schema(title = "Server Ana Kart Seri Numarası")
    private String baseBoardSerialNumber;

    @Schema(title = "Lisans Anahtarı")
    private String license;
}