package com.pzeszko.client;

import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 13.04.2017.
 */
@Data
public class User {
    private String username;
    private String password;
    private List<Role> roles;
}
