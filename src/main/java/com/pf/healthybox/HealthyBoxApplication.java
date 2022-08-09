package com.pf.healthybox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class HealthyBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyBoxApplication.class, args);
    }

}
