package com.pzeszko.microclothes.stock.config.authentication;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by Admin on 26.04.2017.
 */
@Component
public class JwtVerifier {
    @Autowired
    private Environment env;


    public boolean verify(String jwt) throws Exception {
        String[] parts = jwt.split("\\.");

        String decodedSign = new String(Base64.getDecoder().decode(parts[2]));
        return (parts[0] + "." + parts[1]).equals(decrypt(decodedSign.replaceAll("\"", "")));
    }

    private String getKeyValue(String key) {
        int startIdx = StringUtils.ordinalIndexOf(key, "\n", 1);
        int endIdx = StringUtils.ordinalIndexOf(key, "\n", 2);

        return key.substring(startIdx + 1, endIdx);
    }

    private PublicKey getKey(String key) {
        try {
            String keyValue = getKeyValue(key);
            byte[] byteKey = Base64.getDecoder().decode(keyValue.getBytes());
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        } catch (Exception e) {
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
