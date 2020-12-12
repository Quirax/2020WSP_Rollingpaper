package com.quiraxical.rollingpaper;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RSA {
    private static final String SESSION_KEY = "RP_SESS_KEY";
    private static final String SESSION_PKEY = "RP_SESS_PKEY";
    private static final String CRYPT_ALGORITHM = "RSA";
    private static final int CRYPT_KEYSIZE = 1024;

    private static RSA instance = new RSA();

    private RSA() {
    }

    public static RSA getInstance() {
        return instance;
    }

    public void init(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PublicKey publicKey = (PublicKey) session.getAttribute(RSA.SESSION_PKEY);
        
        if (publicKey == null) {
            System.out.println("public key is null");
            KeyPairGenerator gen;
            try {
                gen = KeyPairGenerator.getInstance(RSA.CRYPT_ALGORITHM);
                gen.initialize(RSA.CRYPT_KEYSIZE);

                KeyPair pair = gen.genKeyPair();
                publicKey = pair.getPublic();
                PrivateKey privateKey = pair.getPrivate();

                session.setAttribute(RSA.SESSION_KEY, privateKey);
                session.setAttribute(RSA.SESSION_PKEY, publicKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            RSAPublicKeySpec publicSpec = getKeySpec(publicKey);
            String publicKeyModulus = publicSpec.getModulus().toString(16);
            String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

            request.setAttribute("modulus", publicKeyModulus);
            request.setAttribute("exp", publicKeyExponent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RSAPublicKeySpec getKeySpec(PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance(RSA.CRYPT_ALGORITHM);
        return (RSAPublicKeySpec) factory.getKeySpec(publicKey, RSAPublicKeySpec.class);
    }

    public String decrypt(String encString, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        PrivateKey privateKey = (PrivateKey) session.getAttribute(RSA.SESSION_KEY);

        Cipher cipher = Cipher.getInstance(RSA.CRYPT_ALGORITHM);
        byte[] encBytes = hexToByteArray(encString);

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decBytes = cipher.doFinal(encBytes);
        return new String(decBytes, "utf-8");
    }

    private static byte[] hexToByteArray(String hex) {
        if(hex == null || hex.length() % 2 != 0) { return new byte[] {}; }

        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < hex.length() / 2; i++) {
            byte v = (byte) Integer.parseInt(hex.substring(i * 2, (i + 1) * 2), 16);
            bytes[i] = v;
        }

        return bytes;
    }

    public void destory(HttpServletRequest request) {
        System.out.println("Key is deleted");
        HttpSession session = request.getSession();
        session.removeAttribute(RSA.SESSION_KEY);
        session.removeAttribute(RSA.SESSION_PKEY);
    }
}
