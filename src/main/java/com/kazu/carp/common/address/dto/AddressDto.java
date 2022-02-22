package com.kazu.carp.common.address.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.app.validation.common.FieldNotEmpty;
import com.kazu.carp.common.firm.dto.FirmDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

//import com.sarparda.carp.customer.dto.CustomerDto;

/**
 * Created by sarparda on 10.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AddressDto extends BaseDto {
    @Schema(title = "Adres Tanımı")
    @NotBlank(message = "{notBlank.common.address.name}")
    @Size(min = 1, max = 30, message = "'{common.address.name}' {general.field.size.min.max.error}")
    private String name;

    @Schema(title = "Tam Adres")
    @NotBlank(message = "{notBlank.common.address.address}")
    @Size(min = 1, max = 300, message = "'{common.address.address}' {general.field.size.min.max.error}")
    private String address;

    @Schema(title = "Web Sayfası Adresi")
    @Size(min = 1, max = 100, message = "'{common.address.url}' {general.field.size.min.max.error}")
    private String webUrl;

    @Schema(title = "İlçesi", required = true)
    @FieldNotEmpty(message = "{common.address.county}")
    private CountyDto county;

    @Schema(title = "Mailller")
    private List<MailDto> mails;

    @Schema(title = "Telefonlar")
    private List<PhoneDto> phones;

    @Schema(hidden = true)
    private List<FirmDto> firms;
}
