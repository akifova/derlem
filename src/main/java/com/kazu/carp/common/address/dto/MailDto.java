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
public class MailDto extends BaseDto {
    @Schema(title = "Mail Adresi")
    @NotBlank(message = "{notBlank.common.address.mail.address}")
    @Size(min = 1, max = 100, message = "'{common.address.mail.address}' {general.field.size.min.max.error}")
    private String mailAddress;

    @Schema(title = "Mail Tipi")
    @NotBlank(message = "{notBlank.common.address.mail.type}")
    private MailType mailType;

    @Schema(hidden = true)
    private AddressDto address;
}
