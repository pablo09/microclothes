package com.pzeszko.microclothes.clothes;

import com.pzeszko.microclothes.clothes.config.UserHystrixRequestContext;
import com.pzeszko.microclothes.clothes.config.authentication.JwtAuthentication;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;

/**
 * Created by Admin on 08.04.2017.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
public class ClothesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothesServiceApplication.class);
    }

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {

                Authentication auth = UserHystrixRequestContext.getInstance().get();
                JwtAuthentication jwtAuthentication = (JwtAuthentication) auth;
                requestTemplate.header("Authorization", "Bearer " + jwtAuthentication.getJwt());
            }
        };
    }
}
