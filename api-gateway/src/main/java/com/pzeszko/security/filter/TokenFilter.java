package com.pzeszko.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pzeszko.client.AuthClient;
import com.pzeszko.exception.ErrorCode;
import com.pzeszko.exception.OAuthErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Admin on 25.04.2017.
 */
@Slf4j
public class TokenFilter extends ZuulFilter {

    private AuthClient authClient;
    private ObjectMapper mapper;

    public TokenFilter(AuthClient authClient) {
        mapper = new ObjectMapper();
        this.authClient = authClient;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = extractHeaderToken(request);
        if(token == null) {
            createErrorResponse(ctx, ErrorCode.INVALID_REQUEST);
        } else {
            ResponseEntity<String> responseEntity = null;
            try {
                responseEntity = authClient.authorize(token);
            } catch(Exception e) {
                log.warn("Error occured while processing request");
                createErrorResponse(ctx, ErrorCode.UNAUTHORIZED_CLIENT);
                return null;
            }

            if(!responseEntity.getStatusCode().is2xxSuccessful()) {
                createErrorResponse(ctx, ErrorCode.UNAUTHORIZED_CLIENT);
            } else {
                ctx.addZuulRequestHeader("Authorization", "Bearer " + responseEntity.getBody());
            }
        }


        return null;
    }

    protected String extractHeaderToken(HttpServletRequest request) {
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

        return authHeaderValue;
    }

    private HttpServletResponse createErrorResponse(RequestContext ctx, ErrorCode errorCode) {
        ctx.setSendZuulResponse(false);
        HttpServletResponse response = ctx.getResponse();

        response.setStatus(500);

        try {
            response.getWriter().write(mapper.writeValueAsString(new OAuthErrorResponse(errorCode)));
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
