package com.pzeszko.microclothes.clothes.config;

import com.pzeszko.microclothes.clothes.config.authentication.JwtAuthentication;
import com.pzeszko.microclothes.clothes.config.authentication.decoder.JwtDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class SecurityContextHystrixRequestVariableSetterFilter implements Filter {

    @Autowired
    private Environment env;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String jwt = getJwt(httpServletRequest);
        try {
            if(jwt != null && verify(jwt)) {
                JwtAuthentication auth = new JwtAuthentication(jwt, jwtDecoder.decode(jwt));

                UserHystrixRequestContext.getInstance().set(auth);
                chain.doFilter(request, response);
            } else {
                httpServletResponse.setStatus(403);
            }
        } catch (Exception e) {
            log.error("Exception occured while processing JWT header {}", e);
        }

    }

    private String getJwt(HttpServletRequest httpServletRequest) {
        String jwtHeader = httpServletRequest.getHeader("Authorization");
        int idx = jwtHeader.indexOf(" ");
        return jwtHeader.substring(idx + 1, jwtHeader.length());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void destroy() {}

    private String getKeyValue(String key) {
        int startIdx = StringUtils.ordinalIndexOf(key, "\n", 1);
        int endIdx = StringUtils.ordinalIndexOf(key, "\n", 2);

        return key.substring(startIdx + 1, endIdx);
    }

    private boolean verify(String jwt) throws Exception {
        String[] parts = jwt.split("\\.");

        String decodedSign = new String(Base64.getDecoder().decode(parts[2]));
        return (parts[0] + "." + parts[1]).equals(decrypt(decodedSign.replaceAll("\"", "")));
    }

    private PublicKey getKey(String key){
        try{
            String keyValue = getKeyValue(key);
            byte[] byteKey = Base64.getDecoder().decode(keyValue.getBytes());
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private String decrypt(String cipherText) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        //decriptCipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
        decriptCipher.init(Cipher.DECRYPT_MODE, getKey(env.getProperty("jwt.keyValue")));

        return new String(decriptCipher.doFinal(bytes), "UTF-8");
    }
 
}