package com.pzeszko.dto;

import lombok.Data;

/**
 * Created by Admin on 26.04.2017.
 */
@Data
public class OAuthRequest {
    private String username;
    private String password;
    private String client_id;
    private String client_secret;
    private String grant_type;
    private String code;
    private String redirect_uri;
}
