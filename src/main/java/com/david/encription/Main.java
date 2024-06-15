package com.david.encription;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Main {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String KEY = "7d2dda0f-6acf-4b";
    private static final String FILE_DECRYPTED_PATH = "src/encription/file.txt";
    private static final String FILE_ENCRYPTED_PATH = "src/encription/file.encrypted";

    public static void main(String[] args) throws NoSuchAlgorithmException {

        encrypt(KEY,new File(FILE_DECRYPTED_PATH),new File(FILE_ENCRYPTED_PATH));
        //decrypt(KEY,new File(FILE_ENCRYPTED_PATH),new File(FILE_DECRYPTED_PATH));

    }

    public static void encrypt(String key, File inputFile, File outputFile){
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(String key, File inputFile, File outputFile){
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile)  {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);

            byte[] inputBytes = new byte[(int) inputFile.length()];

            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            inputFile.delete();

        } catch (NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException | IOException | NoSuchPaddingException ex) {
            System.out.println("Error encrypting/decrypting file.txt: " + ex.getMessage());
        }
    }

}
