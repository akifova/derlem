package com.kazu.carp.app.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sarparda on 08.08.2016.
 */
public class KazuLogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //ActiveUser.removeActiveUser(authentication.getName());
    }
}
