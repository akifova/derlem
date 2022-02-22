package com.kazu.carp.security.account.business;

import com.kazu.carp.security.account.domain.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 14.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class CarpActiveUserServiceImpl implements ICarpActiveUserService {
    private static final Logger logger = LoggerFactory.getLogger(CarpActiveUserServiceImpl.class);

    final IUserAccountService userAccountService;

    public CarpActiveUserServiceImpl(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public String getEmail() {
        var email = "anonymous";
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            var user = context.getAuthentication().getPrincipal();
            if(user instanceof String) {
                email = (String) user;
            } else {
                email = ((User) user).getUsername();
            }
        } catch (Exception ex) {
            email = "unknown";
        }

        return email;
    }

    @Override
    public List<String> getRoles() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return null;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return null;
        return authentication.getAuthorities().stream().map(x->x.getAuthority()).collect(Collectors.toList());
    }

    @Override
    public boolean hasRole(String role) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority()) || "ADMIN".equals(auth.getAuthority()))
                return true;
        }
        return false;
    }

    @Override
    public UserAccount findActiveUserAccount() {
       return userAccountService.getByEmail(getEmail());
    }
}