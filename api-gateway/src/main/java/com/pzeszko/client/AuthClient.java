package com.pzeszko.client;

import com.pzeszko.dto.OAuthRequest;
import com.pzeszko.dto.OAuthResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Admin on 25.04.2017.
 */
@FeignClient(name = "uaa-service")
public interface AuthClient {

    @RequestMapping(value = "/oauth2/token")
    ResponseEntity<OAuthResponse> getToken(@RequestBody OAuthRequest oAuthRequest);

    @RequestMapping(value = "/oauth2/authorize", method = RequestMethod.POST)
    ResponseEntity<String> authorize(@RequestBody String token);

    @RequestMapping(value = "/oauth2/userInfo")
    ResponseEntity<String> userInfo(@RequestBody String token);
}
