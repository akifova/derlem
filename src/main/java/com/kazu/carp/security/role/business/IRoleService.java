package com.kazu.carp.security.role.business;


import com.kazu.carp.security.role.domain.UserRole;
import com.kazu.carp.security.role.dto.RoleDto;

import java.util.List;

/**
 * @author akifova
 * @since 16.01.2021
 */
public interface IRoleService {
    UserRole getById(String id);

    RoleDto create(RoleDto roleDto);

    void update(RoleDto roleDto);

    List<RoleDto> findAll();

    RoleDto findOne(String id);

    void remove(String id);
}
