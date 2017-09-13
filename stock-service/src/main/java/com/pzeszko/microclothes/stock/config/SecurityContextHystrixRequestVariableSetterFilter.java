package com.pzeszko.microclothes.stock.config;


import com.pzeszko.microclothes.stock.config.authentication.JwtAuthentication;
import com.pzeszko.microclothes.stock.config.authentication.JwtVerifier;
import com.pzeszko.microclothes.stock.config.authentication.decoder.JwtDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class SecurityContextHystrixRequestVariableSetterFilter implements Filter {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private JwtVerifier jwtVerifier;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String jwt = getJwt(httpServletRequest);
        try {
            if(jwt != null && jwtVerifier.verify(jwt)) {
                JwtAuthentication auth = new JwtAuthentication(jwt, jwtDecoder.decode(jwt));

                UserHystrixRequestContext.getInstance().set(auth);
                chain.doFilter(request, response);
            } else {
                httpServletResponse.setStatus(403);
            }
        } catch (Exception e) {
            log.error("Exception occured while processing JWT header {}", e);
        }

    }

    private String getJwt(HttpServletRequest httpServletRequest) {
        String jwtHeader = httpServletRequest.getHeader("Authorization");
        int idx = jwtHeader.indexOf(" ");
        return jwtHeader.substring(idx + 1, jwtHeader.length());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void destroy() {}


 
}