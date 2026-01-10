package com.david.io;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MappedByteBufferAccess {

    private static final String PATH_FILE = "src/main/resources/io/mapped.txt";

    static void main(String[] args) throws IOException {

        writeUsingMappedBuffer();
        readUsingMappedBuffer();
    }

    private static void writeUsingMappedBuffer() throws IOException {

        try (FileChannel channel = FileChannel.open(
                Path.of(PATH_FILE),
                StandardOpenOption.CREATE,
                StandardOpenOption.READ,
                StandardOpenOption.WRITE)) {

            byte[] data = "Writing using MappedByteBuffer".getBytes();

            MappedByteBuffer buffer =
                    channel.map(FileChannel.MapMode.READ_WRITE, 0, data.length);

            buffer.put(data);
        }
    }

    private static void readUsingMappedBuffer() throws IOException {

        try (FileChannel channel = FileChannel.open(
                Path.of(PATH_FILE),
                StandardOpenOption.READ)) {

            MappedByteBuffer buffer =
                    channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);

            System.out.println(new String(data));
        }
    }

}
