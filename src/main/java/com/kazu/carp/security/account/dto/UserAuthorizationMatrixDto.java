package com.kazu.carp.security.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthorizationMatrixDto {
    private String authorizationId;
    private String userAccountId;
    private String name;
    private String surname;
    private String userName;
    private String roleId;
    private String authority;
    private String roleDefinition;
    private String roleFormLangParam;
    private String roleModuleLangParam;
    private String position;
    private String department;
    private short haveRole;
    private String company;

    public String getFullName() {
        return getName() + " " + getSurname();
    }

    public UserAuthorizationMatrixDto(String authorizationId, String userAccountId, String name, String surname, String userName, String roleId, String authority, String roleDefinition, String roleFormLangParam, String roleModuleLangParam, String position, String department, short haveRole, String company) {
        this.authorizationId = authorizationId;
        this.userAccountId = userAccountId;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.roleId = roleId;
        this.authority = authority;
        this.roleDefinition = roleDefinition;
        this.roleFormLangParam = roleFormLangParam;
        this.roleModuleLangParam = roleModuleLangParam;
        this.position = position;
        this.department = department;
        this.haveRole = haveRole;
        this.company = company;
    }
}