package com.kazu.carp.app.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author akifova
 * 14.05.2021
 */
@Configuration
@SecurityScheme(
        name = "apiKey", // can be set to anything
        type = SecuritySchemeType.APIKEY,
        scheme = "apiKey"
)
public class OpenApiConfiguration {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Derlem")
                        .description("Desen Arşivi")
                        .version("v3.0.24"));
    }

    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .group("Common")
                .pathsToMatch("/common/**")
                .build();
    }

    @Bean
    public GroupedOpenApi dashboardApi() {
        return GroupedOpenApi.builder()
                .group("Dashboard")
                .pathsToMatch("/dashboard/**")
                .build();
    }

    @Bean
    public GroupedOpenApi securityApi() {
        return GroupedOpenApi.builder()
                .group("Security")
                .pathsToMatch("/user/**")
                .build();
    }
    @Bean
    public GroupedOpenApi licenseApi() {
        return GroupedOpenApi.builder()
                .group("License")
                .pathsToMatch("/license/**")
                .build();
    }

    @Bean
    public GroupedOpenApi stockCardApi() {
        return GroupedOpenApi.builder()
                .group("Stock Card")
                .pathsToMatch("/scm/production/definition/stock-card/**")
                .build();
    }

    @Bean
    public GroupedOpenApi yarnApi() {
        return GroupedOpenApi.builder()
                .group("Yarn")
                .pathsToMatch("/yarn/**")
                .build();
    }

    @Bean
    public GroupedOpenApi specimenApi() {
        return GroupedOpenApi.builder()
                .group("Specimen")
                .pathsToMatch("/specimen/**")
                .build();
    }

    @Bean
    public GroupedOpenApi designApi() {
        return GroupedOpenApi.builder()
                .group("Design")
                .pathsToMatch("/design/**")
                .build();
    }

    @Bean
    public GroupedOpenApi imageApi() {
        return GroupedOpenApi.builder()
                .group("Image")
                .pathsToMatch("/image/**")
                .build();
    }

    @Bean
    public GroupedOpenApi archiveApi() {
        return GroupedOpenApi.builder()
                .group("Archive")
                .pathsToMatch("/archive/**")
                .build();
    }
    @Bean
    public GroupedOpenApi fileApi() {
        return GroupedOpenApi.builder()
                .group("File")
                .pathsToMatch("/file/**")
                .build();
    }
}
