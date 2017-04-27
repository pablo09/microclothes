package com.pzeszko.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pzeszko.exception.ErrorCode;
import com.pzeszko.exception.UaaException;
import com.pzeszko.model.JwtHeader;
import com.pzeszko.model.JwtPayload;
import com.pzeszko.model.UserToken;
import com.pzeszko.repository.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Admin on 25.04.2017.
 */
@Service
@Slf4j
public class DefaultJwtGenerator implements JwtGenerator {

    private ObjectMapper mapper;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public DefaultJwtGenerator() {
        mapper = new ObjectMapper();
    }

    @Override
    public String generate(String accessToken) {
        UserDetails userDetails = getUserDetails(accessToken);

        JwtHeader header = createJwtHeader();
        JwtPayload payload = createJwtPayload(userDetails);
        String sign = null;
        try {
            sign = sign(header, payload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return createBase64(header) + "." + createBase64(payload) + "." +createBase64(sign);
    }

    private UserDetails getUserDetails(String accessToken) {
        UserToken userToken = userTokenRepository.findByAccess_Token(accessToken);

        if(userToken == null) {
            log.warn("Token {} not found", accessToken);
            throw new UaaException(ErrorCode.TOKEN_NOT_FOUND);
        }

        long secondsPassed = ChronoUnit.SECONDS.between(userToken.getCreationDate(), LocalDateTime.now());

        if(secondsPassed > userToken.getExpires_in()) {
            log.warn("Token {} has expired", accessToken);
            throw new UaaException(ErrorCode.TOKEN_EXPIRED);
        }

        return customUserDetailsService.loadUserByUsername(userToken.getUsername());
    }

    private String sign(JwtHeader header, JwtPayload payload) throws Exception {
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray())
                .getKeyPair("test");

        String headerStr = createBase64(header);
        String payloadStr = createBase64(payload);

        return encrypt(headerStr + "." + payloadStr, keyPair.getPrivate());
    }

    private String encrypt(String plainText, PrivateKey privateKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    private JwtPayload createJwtPayload(UserDetails userDetails) {
        JwtPayload jwtPayload = new JwtPayload();

        jwtPayload.setUsername(userDetails.getUsername());
        jwtPayload.setClientId("acme");
        jwtPayload.setAuthorities(userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()));
        jwtPayload.setScope(Arrays.asList("openid"));
        jwtPayload.setExp("3600");
        jwtPayload.setJti(UUID.randomUUID().toString());

        return jwtPayload;
    }

    private JwtHeader createJwtHeader() {
        JwtHeader jwtHeader  = new JwtHeader();

        jwtHeader.setAlg("RS256");
        jwtHeader.setTyp("JWT");

        return jwtHeader;
    }

    private String createBase64(Object object) {
        String strObject;
        String base64Str = null;
        try {
            strObject = mapper.writeValueAsString(object);
            base64Str = new String(Base64.getEncoder().encodeToString(strObject.getBytes(StandardCharsets.UTF_8)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return base64Str;
    }
}
