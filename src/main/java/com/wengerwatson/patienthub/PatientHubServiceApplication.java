package com.wengerwatson.patienthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.wengerwatson.patienthub")
@EntityScan(basePackages = "com.wengerwatson.patienthub.model")
@EnableJpaRepositories(basePackages = "com.wengerwatson.patienthub.repository")
public class PatientHubServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatientHubServiceApplication.class, args);
    }
}