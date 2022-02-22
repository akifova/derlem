package com.kazu.carp.security.authorization.business;


import com.kazu.carp.security.authorization.domain.UserAuthorization;
import com.kazu.carp.security.authorization.dto.AuthorizationDto;

import java.util.List;

/**
 * Created by sarparda on 31.07.2016.
 */
public interface IAuthorizationService {

    List<UserAuthorization> findByUserAccountEmail(String email);

    List<AuthorizationDto> findByUserAccountId(String userAccountId);

    AuthorizationDto addRoleToUser(String userId, String roleId);

    void removeAuthorization(String id);

    List<String> getRoleNameListForUserEmail(String email);
}
