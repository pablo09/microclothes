package com.pzeszko.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user-service")
public interface UserClient {

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    ResponseEntity<MicroclothesUserDetails> getUserByName(@PathVariable("username") String username);
}
