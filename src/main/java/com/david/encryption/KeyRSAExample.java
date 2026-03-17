package com.david.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeyRSAExample {
    static void main(String[] args) {
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");

            keyGen.initialize(2048);

            KeyPair pair = keyGen.generateKeyPair();

            var publicKey = pair.getPublic();
            var privateKey = pair.getPrivate();

            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            System.out.println("Public Key Base64: " + publicKeyBase64);
            System.out.println("Private Key Base64: " + privateKeyBase64);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
