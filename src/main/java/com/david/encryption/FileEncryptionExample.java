package com.david.encryption;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class FileEncryptionExample {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String KEY = "7d2dda0f-6acf-4b";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;
    private static final String FILE_DECRYPTED_PATH = "src/main/resources/encryption/file.txt";
    private static final String FILE_ENCRYPTED_PATH = "src/main/resources/encryption/file.encrypted";

    static void main(String[] args) {
        SecretKey key = getKey();
        encrypt(key, new File(FILE_DECRYPTED_PATH), new File(FILE_ENCRYPTED_PATH));
        //decrypt(key, new File(FILE_ENCRYPTED_PATH), new File(FILE_DECRYPTED_PATH));

    }

    public static SecretKey getKey() {
        return new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    }

    public static void encrypt(SecretKey key, File inputFile, File outputFile) {
        try (
                FileInputStream fis = new FileInputStream(inputFile);
                FileOutputStream fos = new FileOutputStream(outputFile)
        ) {
            byte[] iv = new byte[GCM_IV_LENGTH];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            // Write IV at the start of the file
            fos.write(iv);

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] encrypted = cipher.update(buffer, 0, bytesRead);

                if (encrypted != null) {
                    fos.write(encrypted);
                }
            }

            byte[] finalBytes = cipher.doFinal();

            if (finalBytes != null) {
                fos.write(finalBytes);
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException | IOException | InvalidAlgorithmParameterException |
                 NoSuchPaddingException ex) {
            System.out.println("Error encrypting/decrypting file.txt: " + ex.getMessage());
        }
    }

    public static void decrypt(SecretKey key, File inputFile, File outputFile) {
        try (
                FileInputStream fis = new FileInputStream(inputFile);
                FileOutputStream fos = new FileOutputStream(outputFile)
        ) {
            byte[] iv = new byte[GCM_IV_LENGTH];
            fis.read(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] decrypted = cipher.update(buffer, 0, bytesRead);

                if (decrypted != null) {
                    fos.write(decrypted);
                }
            }

            byte[] finalBytes = cipher.doFinal();

            if (finalBytes != null) {
                fos.write(finalBytes);
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException | IOException | InvalidAlgorithmParameterException |
                 NoSuchPaddingException ex) {
            System.out.println("Error encrypting/decrypting file.txt: " + ex.getMessage());
        }
    }


}
