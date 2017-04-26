package com.pzeszko.service;

import com.pzeszko.dto.OAuthRequest;
import com.pzeszko.dto.OAuthResponse;
import com.pzeszko.exception.ErrorCode;
import com.pzeszko.exception.UaaException;
import com.pzeszko.model.UserToken;
import com.pzeszko.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Admin on 26.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class DefaultAccessTokenGenerator implements AccessTokenGenerator{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Transactional
    @Override
    public OAuthResponse generateAccessToken(OAuthRequest oAuthRequest) {
        UserDetails user = customUserDetailsService.loadUserByUsername(oAuthRequest.getUsername());
        if(!user.getPassword().equals(oAuthRequest.getPassword())) {
            throw new UaaException(ErrorCode.INCORRECT_PASSWORD);
        }

        UserToken entity = createUserToken(oAuthRequest);
        userTokenRepository.save(entity);

        return createOAuthResponse(entity);
    }

    private OAuthResponse createOAuthResponse(UserToken userToken) {
        OAuthResponse oAuthResponse = new OAuthResponse();

        oAuthResponse.setRefresh_token(userToken.getRefresh_token());
        oAuthResponse.setAccess_token(userToken.getAccess_token());
        oAuthResponse.setToken_type(userToken.getToken_type());
        oAuthResponse.setScope(userToken.getScope());
        oAuthResponse.setExpires_in(String.valueOf(userToken.getExpires_in()));

        return oAuthResponse;
    }

    private UserToken createUserToken(OAuthRequest oAuthRequest) {
        UserToken userToken = new UserToken();

        userToken.setUsername(oAuthRequest.getUsername());
        userToken.setScope("openid");
        userToken.setExpires_in(3600L);
        userToken.setCreationDate(LocalDateTime.now());

        userToken.setAccess_token(generateToken());
        userToken.setRefresh_token(generateToken());
        userToken.setToken_type("Bearer");
        return userToken;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
