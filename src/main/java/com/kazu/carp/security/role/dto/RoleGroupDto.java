package com.kazu.carp.security.role.dto;

import com.kazu.carp.app.jpa.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by sarparda on 01.08.2016.
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleGroupDto extends BaseDto {

    private String id;
    private String name;
    private List<RoleToGroupDto> roleToGroups;
}