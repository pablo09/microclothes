package com.pzeszko.microclothes.shoes.config.authentication;

import com.pzeszko.microclothes.shoes.config.authentication.decoder.JwtPayload;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Admin on 26.04.2017.
 */
public class JwtAuthentication implements Authentication {

    private String jwt;
    private JwtPayload payload;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtAuthentication(String jwt, JwtPayload payload) {
        this.jwt = jwt;
        this.payload = payload;
        this.authorities = payload.getAuthorities().stream().map(auth -> new SimpleGrantedAuthority(auth)).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return null;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
