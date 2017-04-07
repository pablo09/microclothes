package com.pzeszko.microclothes.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Admin on 04.04.2017.
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableEurekaClient
public class UIApplication {

    public static void main(String[] args) {
        SpringApplication.run(UIApplication.class);
    }
}
