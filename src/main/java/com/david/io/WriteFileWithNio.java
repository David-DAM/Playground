package com.david.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WriteFileWithNio {

    private static final String PATH_FILE = "src/main/resources/io/test.txt";

    static void main(String[] args) throws IOException {

        String content = "Writing file using Java NIO FileChannel";

        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());

        try (FileChannel channel = FileChannel.open(
                Path.of(PATH_FILE),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            channel.write(buffer);
        }
    }

}
