package com.kazu.carp.security.account.business;

import com.kazu.carp.security.account.domain.UserAccount;

import java.util.List;

/**
 * @author akifova
 * 14.02.2021
 */
public interface ICarpActiveUserService {
    String getEmail();
    List<String> getRoles();
    boolean hasRole(String role);

    UserAccount findActiveUserAccount();
}
