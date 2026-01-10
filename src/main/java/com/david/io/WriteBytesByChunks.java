package com.david.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBytesByChunks {

    private static final String PATH_FILE = "src/main/resources/io/test.txt";
    private static final int BUFFER_SIZE = 1024;

    static void main(String[] args) throws IOException {

        byte[] data = "Writing bytes by chunks example".getBytes();

        writeByChunks(data);
    }

    private static void writeByChunks(byte[] data) throws IOException {

        try (BufferedOutputStream outputStream =
                     new BufferedOutputStream(new FileOutputStream(PATH_FILE))) {

            int written = 0;

            while (written < data.length) {
                int chunkSize = Math.min(BUFFER_SIZE, data.length - written);
                outputStream.write(data, written, chunkSize);
                written += chunkSize;
            }
        }
    }

}
