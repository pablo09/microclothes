package com.pzeszko.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Admin on 25.04.2017.
 */
@Data
public class JwtPayload {
    private String exp;
    private String username;
    private List<String> authorities;
    private String jti;
    private String clientId;
    private List<String> scope;
}
