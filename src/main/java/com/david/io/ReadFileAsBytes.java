package com.david.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class ReadFileAsBytes {

    private static final String PATH_FILE = "src/main/resources/io/test.txt";
    private static final int BUFFER_SIZE = 1024;

    static void main(String[] args) throws IOException {

        byte[] content = readFile();

        System.out.println("Bytes read: " + content.length);
    }

    private static byte[] readFile() throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (BufferedInputStream inputStream =
                     new BufferedInputStream(new FileInputStream(Path.of(PATH_FILE).toFile()))) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        return outputStream.toByteArray();
    }

}
