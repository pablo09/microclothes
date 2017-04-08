package com.pzeszko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Thibaud Leprêtre
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) {
        REGISTERED_USERS.put("user", new UserModel(1,"user", "password", "USER"));
        REGISTERED_USERS.put("admin", new UserModel(2, "admin", "admin", "ADMIN"));

        SpringApplication.run(UserServiceApplication.class, args);
    }

    private static Map<String,UserModel> REGISTERED_USERS = new HashMap<>();
    private static AtomicLong CURR = new AtomicLong(3L);

    @Controller
    public static class UserController {

        @RequestMapping(value = "/user/register", method = RequestMethod.POST)
        public ResponseEntity<UserModel> registerNewUser(@RequestBody NewUser newUser) {
            UserModel userModel = new UserModel(CURR.getAndIncrement(), newUser.getUsername(), newUser.getPassword(), newUser.getRole());
            REGISTERED_USERS.putIfAbsent(newUser.getUsername(), userModel);
            LOG.info("UserServiceApplication.registerNewUser: " + REGISTERED_USERS);
            return ResponseEntity.ok(userModel);
        }

        @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
        public ResponseEntity<UserModel> getUserByName(@PathVariable String username) {
            UserModel userModel = REGISTERED_USERS.get(username);
            LOG.info("UserServiceApplication.getUserByName: " + username + " -> " + userModel);
            return ResponseEntity.ok(userModel);
        }
    }


}
