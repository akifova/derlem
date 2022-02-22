package com.kazu.carp.security.account.dto;

import com.kazu.carp.app.validation.common.FieldMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author akifova
 * 10.11.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@FieldMatch(first = "newPassword", second = "confirmPassword", message = "{notMatch.security.userAccount.passwords}")
public class ResetPasswordDto {
    @Schema(title = "Kullanıcı ID")
    @NotBlank(message = "{notEmpty.security.userAccount.password.userId}")
    private String userId;

    @Schema(title = "Yeni Şifre")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&+_%*()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "{regex.security.userAccount.password}")
    private String newPassword;

    @Schema(title = "Yeni Şifre (Tekrar)")
    private String confirmPassword;
}
