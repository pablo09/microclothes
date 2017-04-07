package com.kakawait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
/**
 * @author Thibaud Leprêtre
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DummyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DummyServiceApplication.class, args);
    }
	


    @RestController
    @RequestMapping("/")
    public static class DummyController {

        @RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
        public String helloWorld(Principal principal) {
            return principal == null ? "Hello anonymous" : "Helsdlo " + principal.getName();
        }


        @PreAuthorize("#oauth2.hasScope('openid') and hasRole('ROLE_ADMIN')")
        @RequestMapping(value = "secret", method = RequestMethod.GET)
        public String helloSecret(Principal principal) {
            return principal == null ? "Hellosd anonymous" : "S3CR3T  - Hello " + principal.getName();
        }
    }
}
