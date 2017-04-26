package com.pzeszko.service;

/**
 * Created by Admin on 25.04.2017.
 */
public interface JwtGenerator {

    String generate(String accessToken);
}
