package com.kazu.carp.security.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.design.plan.dto.DesignPlanDto;
import com.kazu.carp.security.authorization.dto.AuthorizationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Created by sarparda on 31.07.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccountDto extends BaseDto {

    @Schema(title = "Kullanıcı E-Mail Adresi. (Unique)")
    @Email(message = "{notValid.security.userAccount.email}")
    @NotEmpty(message = "{notEmpty.security.userAccount.email}")
    @Size(min = 7, max = 100, message = "{security.userAccount.email} {general.field.size.min.max.error}")
    private String email;

    @Schema(title = "Kullanıcı Şifresi")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&+_%*()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "{regex.security.userAccount.password}")
    private String password;

    @Schema(title = "Kullanıcı İsmi")
    @NotBlank(message = "{notBlank.security.userAccount.name}")
    @Size(min = 1, max = 100, message = "'{security.userAccount.name}' {general.field.size.min.max.error}")
    private String name;

    @Schema(title = "Kullanıcı Soyismi")
    @NotBlank(message = "{notBlank.security.userAccount.surname}")
    @Size(min = 1, max = 100, message = "'{security.userAccount.surname}' {general.field.size.min.max.error}")
    private String surname;

    @Schema(hidden = true)
    public String getNameSurname() {
        return String.format("%s %s", this.name, this.surname);
    }

    @Schema(title = "Kullanıcı Yetkileri")
    @Size(min = 1, message = "{size.security.userAccount.authorizations}")
    private List<AuthorizationDto> authorizations;

    @Schema(hidden = true)
    private List<DesignPlanDto> designPlans;
}
