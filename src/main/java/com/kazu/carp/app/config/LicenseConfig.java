package com.kazu.carp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author akifova
 * 06.10.2021
 */
@Configuration
@EnableScheduling
public class LicenseConfig {
    @Bean
    public String license() {
        return "";
    }
}
