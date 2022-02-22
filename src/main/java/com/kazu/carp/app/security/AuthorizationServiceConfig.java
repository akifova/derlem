package com.kazu.carp.app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author akifova
 * @since 16.01.2021
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.kazu")
@Order(value = 1)
public class AuthorizationServiceConfig {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceConfig.class);

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
