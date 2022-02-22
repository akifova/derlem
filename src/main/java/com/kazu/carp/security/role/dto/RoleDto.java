package com.kazu.carp.security.role.dto;

import com.kazu.carp.app.jpa.BaseDto;
import com.kazu.carp.security.authorization.dto.AuthorizationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Created by sarparda on 31.07.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class RoleDto extends BaseDto {
    @Schema(title = "Role Adı")
    private String authority;

    @Schema(title = "Role Tanımı Dil Parametresi")
    private String langParam;

    @Schema(title = "Modül Tanımı Dil Parametresi")
    private String moduleLangParam;

    @Schema(title = "Form Tanımı Dil Parametresi")
    private String formLangParam;

    @Schema(title = "Role için Açıklama")
    private String description;

    @Schema(hidden = true)
    private List<AuthorizationDto> authorizations;

    @Schema(hidden = true)
    private List<RoleToGroupDto> roleToGroups;
}
