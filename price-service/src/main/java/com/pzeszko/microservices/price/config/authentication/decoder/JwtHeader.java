package com.pzeszko.microservices.price.config.authentication.decoder;

import lombok.Data;

/**
 * Created by Admin on 25.04.2017.
 */
@Data
public class JwtHeader {
    private String alg;
    private String typ;
}
