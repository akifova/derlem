package com.kazu.carp.security.authentication.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akifova
 * 17.04.2021
 */
@RestController
@RequestMapping("/user/authentication")
@Tag(name = "Login Kontrolleri", description = "")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/check")
    public ResponseEntity check() {
        return ResponseEntity.ok().build();
    }
}