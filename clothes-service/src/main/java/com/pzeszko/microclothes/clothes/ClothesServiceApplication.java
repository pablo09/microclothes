package com.pzeszko.microclothes.clothes;

import com.pzeszko.microclothes.clothes.config.UserHystrixRequestContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Created by Admin on 08.04.2017.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
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
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
                requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());
            }
        };
    }
}
