package com.david.encryption;

import java.security.MessageDigest;

public class HashEncryptionExample {
    static void main() {
        String text = "Hello World!";
        System.out.println("Text: " + text);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(text.getBytes());

            byte[] digest = md.digest();

            System.out.println("Hash: ");
            for (byte b : digest) {
                System.out.format("%02x", b);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
