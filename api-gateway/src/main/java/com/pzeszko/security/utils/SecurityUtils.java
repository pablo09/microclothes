package com.pzeszko.security.utils;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Admin on 27.04.2017.
 */
public class SecurityUtils {

    public static String extractHeaderToken(HttpServletRequest request) {
        Enumeration headers = request.getHeaders("Authorization");

        String value;
        do {
            if(!headers.hasMoreElements()) {
                return null;
            }

            value = (String)headers.nextElement();
        } while(!value.toLowerCase().startsWith("Bearer".toLowerCase()));

        String authHeaderValue = value.substring("Bearer".length()).trim();
        request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, value.substring(0, "Bearer".length()).trim());
        int commaIndex = authHeaderValue.indexOf(44);
        if(commaIndex > 0) {
            authHeaderValue = authHeaderValue.substring(0, commaIndex);
        }

        if(authHeaderValue == null || authHeaderValue.equals("null") || authHeaderValue.equals("undefined")) {
            return null;
        }

        return authHeaderValue;
    }

    public static String extractToken(String token) {
        String authHeaderValue = token.substring("Bearer".length()).trim();
        int commaIndex = authHeaderValue.indexOf(44);
        if(commaIndex > 0) {
            authHeaderValue = authHeaderValue.substring(0, commaIndex);
        }

        return authHeaderValue;
    }
}
