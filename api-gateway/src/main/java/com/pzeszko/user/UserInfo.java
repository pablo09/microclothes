package com.pzeszko.user;

import java.security.Principal;

/**
 * Created by Admin on 06.04.2017.
 */
public class UserInfo {
    private String username;

    public UserInfo(Principal principal) {
        username = principal.getName();
    }

    public String getUsername() {
        return username;
    }
}
