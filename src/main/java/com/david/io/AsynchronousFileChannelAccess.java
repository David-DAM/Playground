package com.david.io;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousFileChannelAccess {

    private static final String PATH_FILE = "src/main/resources/io/async.txt";

    public static void main(String[] args) throws Exception {

        writeAsync();
        readAsync();
    }

    private static void writeAsync() throws Exception {

        try (AsynchronousFileChannel channel =
                     AsynchronousFileChannel.open(
                             Path.of(PATH_FILE),
                             StandardOpenOption.CREATE,
                             StandardOpenOption.WRITE)) {

            ByteBuffer buffer =
                    ByteBuffer.wrap("Async write with AsynchronousFileChannel".getBytes());

            Future<Integer> result = channel.write(buffer, 0);

            result.get();
        }
    }

    private static void readAsync() throws Exception {

        try (AsynchronousFileChannel channel =
                     AsynchronousFileChannel.open(
                             Path.of(PATH_FILE),
                             StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            Future<Integer> result = channel.read(buffer, 0);

            result.get();

            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.limit()));
        }
    }

}
