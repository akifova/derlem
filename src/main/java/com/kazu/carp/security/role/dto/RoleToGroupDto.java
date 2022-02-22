package com.kazu.carp.security.role.dto;

import com.kazu.carp.app.jpa.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by sarparda on 01.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleToGroupDto extends BaseDto {

    private String id;
    private RoleDto role ;
    private RoleGroupDto roleGroup;
}