package com.kazu.carp.security.authorization.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.security.account.dto.UserAccountDto;
import com.kazu.carp.security.role.dto.RoleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by sarparda on 31.07.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AuthorizationDto extends BaseDto {
    @Schema(title = "Kullanıcı Tanımı")
    private UserAccountDto userAccount;

    @Schema(title = "Kullanıcı Rolü")
    private RoleDto role;
}
