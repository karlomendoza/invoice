package com.example.restservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String NAME = "Digital OnUs";
    private static final String URL = "http://digitalonus.com";
    private static final String EMAIL = "reachus@digitalonus.com";
    private static final String TITLE = "Invoice demo app";
    private static final String DESCRIPTION = "This is a basic service to create an invoice";
    private static final String VERSION = "1.0";

    @Bean
    public Docket api() {
        Contact contact = new Contact(NAME, URL, EMAIL);
        ApiInfo apiInfo = new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                "",
                contact,
                "",
                "",
                new ArrayList<>());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}
