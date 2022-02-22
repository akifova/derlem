package com.kazu.carp.security.authorization.business;

import com.kazu.carp.security.account.business.IUserAccountService;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.security.account.dto.IUserAccountMapper;
import com.kazu.carp.security.authorization.domain.UserAuthorization;
import com.kazu.carp.security.authorization.dto.AuthorizationDto;
import com.kazu.carp.security.authorization.persistence.IAuthorizationDao;
import com.kazu.carp.security.role.business.IRoleService;
import com.kazu.carp.security.role.domain.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * @since 12.01.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = { "Exception" })
public class AuthorizationServiceImpl implements Serializable, IAuthorizationService {
   
    final
    IAuthorizationDao dao;

    final IUserAccountService userAccountService;

    final IRoleService roleService;

    final IUserAccountMapper userAccountMapper;

    public AuthorizationServiceImpl(IAuthorizationDao dao, IUserAccountService userAccountService, IRoleService roleService, IUserAccountMapper userAccountMapper) {
        this.dao = dao;
        this.userAccountService = userAccountService;
        this.roleService = roleService;
        this.userAccountMapper = userAccountMapper;
    }

    @Override
    public List<UserAuthorization> findByUserAccountEmail(String email) {
        List<UserAuthorization> authorizations = dao.findByUserAccountEmail(email);
        return authorizations;
    }

    @Override
    public List<AuthorizationDto> findByUserAccountId(String userAccountId) {
        List<UserAuthorization> authorizations = dao.findByUserAccountId(userAccountId);
        return authorizations.stream().map(m -> {
            AuthorizationDto dto = userAccountMapper.toDto(m);
            dto.setRole(userAccountMapper.toDto(m.getRole()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public AuthorizationDto addRoleToUser(String userId, String roleId) {
        UserAccount userAccount = userAccountService.getById(userId);
        UserRole role = roleService.getById(roleId);
        UserAuthorization authorization = UserAuthorization.builder().userAccount(userAccount).role(role).build();
        dao.save(authorization);
        return userAccountMapper.toDto(authorization);
    }

    @Override
    public void removeAuthorization(String id) {
        UserAuthorization auth = dao.getById(id);
        dao.delete(auth);
    }

    @Override
    public List<String> getRoleNameListForUserEmail(String email) {
        List<UserAuthorization> authorizations = findByUserAccountEmail(email);
        return authorizations.stream().map(m->m.getRole().getAuthority()).collect(Collectors.toList());
    }

    /*@Override
    public List<AuthorizationDto> findByAuthorizationsWithRolesByUserId(String userId) {
        UserAccount userAccount = userAccountService.getById(userId);
        List<AuthorizationDto> authorizationDtoList = userAccount.getUserAuthorizations().stream()
                .map(m->{
                    AuthorizationDto authorizationDto = Mapper.map(m);
                    authorizationDto.setRole(Mapper.map(m.getUserRole()));
                    authorizationDto.setActive(true);
                    return authorizationDto;
                }).collect(Collectors.toList());

        List<RoleDto> allRoles = roleService.qryRoleList();
        allRoles.stream().forEach(roleDto -> {
            if(!authorizationDtoList.stream().anyMatch(a-> a.getRole().getId().equals(roleDto.getId()))) {
                AuthorizationDto authDto = new AuthorizationDto();
                authDto.setRole(roleDto);
                authDto.setActive(false);
                authorizationDtoList.add(authDto);
            }
        });

        return authorizationDtoList;
    }*/
}
