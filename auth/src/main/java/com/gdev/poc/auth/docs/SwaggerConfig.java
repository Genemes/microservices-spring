package com.gdev.poc.auth.docs;

import org.springframework.context.annotation.Configuration;

import com.gdev.poc.core.docs.BaseSwaggerConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig() {
        super("com.gdev.poc.auth.endpoint.controller");
    }
}
