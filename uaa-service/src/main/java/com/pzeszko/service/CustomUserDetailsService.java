package com.pzeszko.service;/*

import com.pzeszko.client.MicroclothesUserDetails;
import com.pzeszko.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by mavogel on 1/14/17.
 */

import com.pzeszko.client.MicroclothesUserDetails;
import com.pzeszko.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        MicroclothesUserDetails userDetails = this.userClient.getUserByName(username).getBody();

        if (userDetails == null) {
            LOG.info("User {} not found", username);
            throw new UsernameNotFoundException(username);
        }

        return userDetails;
    }
}
