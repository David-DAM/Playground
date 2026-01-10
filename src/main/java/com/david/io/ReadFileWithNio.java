package com.david.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ReadFileWithNio {

    private static final String PATH_FILE = "src/main/resources/io/test.txt";
    private static final int BUFFER_SIZE = 1024;

    static void main(String[] args) throws IOException {

        try (FileChannel channel = FileChannel.open(
                Path.of(PATH_FILE),
                StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (channel.read(buffer) > 0) {
                buffer.flip();
                System.out.print(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        }
    }

}
