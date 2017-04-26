package com.pzeszko.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Admin on 25.04.2017.
 */
@Data
@Builder
public class OAuthResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
}
