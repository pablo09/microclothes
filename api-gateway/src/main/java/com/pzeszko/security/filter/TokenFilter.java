package com.pzeszko.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pzeszko.client.AuthClient;
import com.pzeszko.exception.ErrorCode;
import com.pzeszko.exception.OAuthErrorResponse;
import com.pzeszko.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String token = SecurityUtils.extractHeaderToken(request);
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

    private HttpServletResponse createErrorResponse(RequestContext ctx, ErrorCode errorCode) {
        ctx.setSendZuulResponse(false);
        HttpServletResponse response = ctx.getResponse();

        response.setStatus(403);

        try {
            response.getWriter().write(mapper.writeValueAsString(new OAuthErrorResponse(errorCode)));
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
