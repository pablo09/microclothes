package com.pzeszko.web;

import com.pzeszko.dto.OAuthRequest;
import com.pzeszko.dto.OAuthResponse;
import com.pzeszko.service.AccessTokenGenerator;
import com.pzeszko.service.CustomUserDetailsService;
import com.pzeszko.service.JwtGenerator;
import com.pzeszko.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Admin on 26.04.2017.
 */
@Controller
@RequestMapping("/oauth2")
public class OAuthController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private AccessTokenGenerator accessTokenGenerator;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/token")
    public ResponseEntity<OAuthResponse> getToken(@RequestBody OAuthRequest oAuthRequest) {
        return ResponseEntity.ok(accessTokenGenerator.generateAccessToken(oAuthRequest));
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ResponseEntity<String> authorize(@RequestBody String token) {
        return ResponseEntity.ok(jwtGenerator.generate(token));
    }

    @RequestMapping(value = "/userInfo")
    public ResponseEntity<String> userInfo(@RequestBody String token) {
        return ResponseEntity.ok(userInfoService.getUsername(token));
    }
}
