package com.pzeszko.service;

import com.pzeszko.client.AuthClient;
import com.pzeszko.dto.OAuthRequest;
import com.pzeszko.dto.OAuthResponse;
import com.pzeszko.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 26.04.2017.
 */
@Service
public class OAuthServiceImpl implements OAuthService{

    @Autowired
    private AuthClient authClient;

    @Override
    public OAuthResponse getToken(OAuthRequest request) {
        ResponseEntity<OAuthResponse> oAuthResponseResponseEntity = authClient.getToken(request);
        if(oAuthResponseResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return oAuthResponseResponseEntity.getBody();
        }

        return null;
    }

    @Override
    public String getUserInfo(HttpServletRequest request) {
        String token = SecurityUtils.extractHeaderToken(request);
        if(token == null) {
            return null;
        }

        ResponseEntity<String> responseEntity = authClient.userInfo(token);
        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        return responseEntity.getBody();
    }
}
