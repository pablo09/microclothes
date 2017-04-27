package com.pzeszko.web;

import com.pzeszko.dto.OAuthRequest;
import com.pzeszko.dto.OAuthResponse;
import com.pzeszko.service.OAuthService;
import com.pzeszko.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 26.04.2017.
 */
@RestController
public class OAuthController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    public ResponseEntity<OAuthResponse> getToken(@RequestBody OAuthRequest request) {
        return ResponseEntity.ok(oAuthService.getToken(request));
    }

    @RequestMapping("/me")
    public UserInfo userInfo(HttpServletRequest request) {
        return new UserInfo(oAuthService.getUserInfo(request));
    }
}
