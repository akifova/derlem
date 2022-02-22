package com.kazu.carp.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kazu.carp.app.license.business.LicenseCheckInterceptor;
import com.kazu.carp.common.file.util.business.IFileUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.text.DateFormat;
import java.time.Duration;

/**
 * @author akifova
 * 28.01.2021
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.kazu")
public class ApplicationConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    final IFileUtilService fileUtilService;

    public ApplicationConfig(IFileUtilService fileUtilService) {
        this.fileUtilService = fileUtilService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(licenseCheckInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + multipartConfigElement().getLocation());
    }

    @Bean
    LicenseCheckInterceptor licenseCheckInterceptor() {
        return new LicenseCheckInterceptor();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .rootUri("http://localhost:8090")
                .build();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        final String rootFolder = fileUtilService.getRootFolderAddressForUploads();
        final String thumbFolder = rootFolder + "thumbnails/";
        fileUtilService.checkDirectoryExists(rootFolder);
        fileUtilService.checkDirectoryExists(thumbFolder);

        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofGigabytes(2));
        factory.setMaxRequestSize(DataSize.ofGigabytes(20));
        factory.setLocation(thumbFolder);

        System.out.println("*****************  Upload Address *********************");
        System.out.println(thumbFolder);
        System.out.println("*******************************************************");

        return factory.createMultipartConfig();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MessageSource validationMessages() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(validationMessages());
        return bean;
    }

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(DateFormat.getTimeInstance(DateFormat.FULL));
        return mapper;
    }
}
