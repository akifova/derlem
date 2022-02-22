package com.kazu.carp.security.account.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.security.authorization.domain.UserAuthorization;
import com.kazu.carp.security.authorization.dto.AuthorizationDto;
import com.kazu.carp.security.role.domain.UserRole;
import com.kazu.carp.security.role.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * @since 13.01.2021
 */
@Mapper(config = MapStructConfig.class)
public interface IUserAccountMapper {

    @Mappings({
            @Mapping(target = "designPlans", ignore = true),
            @Mapping(target = "authorizations", ignore = true)
    })
    UserAccount toEntity(UserAccountDto userAccountDto);

    @Mappings({
            @Mapping(target = "designPlans", ignore = true),
            @Mapping(target = "authorizations", ignore = true),
            @Mapping(target = "password", ignore = true)
    })
    UserAccountDto toDto(UserAccount userAccount);

    UserAuthorization toEntity(AuthorizationDto authorizationDto);

    AuthorizationDto toDto(UserAuthorization authorization);

    @Mappings({
            @Mapping(target = "authorizations", ignore = true),
            @Mapping(target = "roleToGroups", ignore = true)
    })
    UserRole toEntity(RoleDto roleDto);

    @Mappings({
            @Mapping(target = "authorizations", ignore = true),
            @Mapping(target = "roleToGroups", ignore = true)
    })
    RoleDto toDto(UserRole role);
}
