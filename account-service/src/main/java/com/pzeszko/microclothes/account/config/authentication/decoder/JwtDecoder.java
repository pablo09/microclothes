package com.pzeszko.microclothes.account.config.authentication.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by Admin on 26.04.2017.
 */
@Component
public class JwtDecoder {

    private ObjectMapper objectMapper;

    public JwtDecoder() {
        objectMapper = new ObjectMapper();
    }

    public JwtPayload decode(String jwt) throws IOException {
        String[] parts = jwt.split("\\.");

        String header = decodeBase64(parts[0]);
        String payload = decodeBase64(parts[1]);

        return objectMapper.readValue(payload, JwtPayload.class);
    }

    private String decodeBase64(String part) {
        return new String(Base64.getDecoder().decode(part));
    }
}
