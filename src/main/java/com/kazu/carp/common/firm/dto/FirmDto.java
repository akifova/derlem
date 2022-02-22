package com.kazu.carp.common.firm.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.common.address.dto.AddressDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author akifova
 * 29.04.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class FirmDto extends BaseDto implements Serializable {
    @Schema(title = "Firma Adı")
    @NotBlank(message = "{notBlank.common.firm.name}")
    @Size(min = 1, max = 100, message = "'{common.firm.name}' {general.field.size.min.max.error}")
    private String name;

    @Schema(title = "Firma Ticari Ünvanı")
    @NotBlank(message = "{notBlank.common.firm.tradeName}")
    @Size(min = 1, max = 100, message = "'{common.firm.tradeName}' {general.field.size.min.max.error}")
    private String tradeName;

    @Schema(title = "Vergi Dairesi")
    private String taxOffice;

    @Schema(title = "Vergi Numarası")
    private String taxNumber;

    @Schema(title = "Tedarikçi mi?")
    @NotNull(message = "{notNull.common.firm.supplier}")
    private boolean supplier;

    @Schema(title = "Müşteri mi?")
    @NotNull(message = "{notNull.common.firm.customer}")
    private boolean customer;

    @Schema(title = "Fason Üretici mi?")
    @NotNull(message = "{notNull.common.firm.outsourcer}")
    private boolean outsourcer;

    @Schema(title = "Şahıs Şirketi mi?")
    @NotNull(message = "{notNull.common.firm.isPersonCompany}")
    private short isPersonCompany;

    @Schema(title = "Firma Adresi")
    private AddressDto address;
}