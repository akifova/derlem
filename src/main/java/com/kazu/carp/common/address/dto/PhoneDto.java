package com.kazu.carp.common.address.dto;

import com.kazu.carp.app.jpa.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by sarparda on 10.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PhoneDto extends BaseDto {
    @Schema(title = "Telefon Numarası")
    @NotBlank(message = "{notBlank.common.address.phone.number}")
    @Size(min = 1, max = 30, message = "'{common.address.phone.number}' {general.field.size.min.max.error}")
    private String phoneNumber;

    @Schema(title = "Telefon Tipi")
    @NotBlank(message = "{notBlank.common.address.phone.type}")
    private PhoneType phoneType;

    @Schema(hidden = true)
    private AddressDto address;
}
