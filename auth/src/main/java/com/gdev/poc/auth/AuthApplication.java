package com.gdev.poc.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gdev.poc.core.property.JwtConfiguration;

@SpringBootApplication
@EntityScan({"com.gdev.poc.core.model"})
@EnableJpaRepositories({"com.gdev.poc.core.repository"})
@EnableEurekaClient
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("com.gdev.poc")
public class AuthApplication {

    public static void main(String[] args) {
    	System.out.println("---------------Password:-------------");
    	System.out.println(new BCryptPasswordEncoder().encode("admin"));
        SpringApplication.run(AuthApplication.class, args);
    }

}
