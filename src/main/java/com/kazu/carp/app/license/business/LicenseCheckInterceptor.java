package com.kazu.carp.app.license.business;

import com.kazu.carp.app.exception.app.InvalidLicenseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akifova
 * 07.10.2021
 */
public class LicenseCheckInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LicenseCheckInterceptor.class);

    @Autowired
    ILicenseService licenseService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURI().replace(request.getContextPath(), "").equals("/") ||
                request.getRequestURI().contains("license/") ||
                request.getRequestURI().contains("/css/") ||
                request.getRequestURI().contains("/js/") ||
                request.getRequestURI().contains("/fonts/") ||
                request.getRequestURI().contains("/user/authentication/check") ||
                request.getRequestURI().contains("/user/account/get/") ||
                licenseService.verifyLicense()) {
            return true;
        } else {
            try {
                throw new InvalidLicenseException(request.getRequestURI().replace(request.getContextPath(), ""));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }
}
