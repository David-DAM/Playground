package com.david.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesNewBufferedWriterAndReader {

    private static final String PATH_FILE = "src/main/resources/io/nio_buffered.txt";

    static void main(String[] args) throws IOException {

        write();
        read();
    }

    private static void write() throws IOException {

        try (var writer = Files.newBufferedWriter(Path.of(PATH_FILE))) {
            writer.write("Line 1");
            writer.newLine();
            writer.write("Line 2");
        }
    }

    private static void read() throws IOException {

        try (var reader = Files.newBufferedReader(Path.of(PATH_FILE))) {
            reader.lines().forEach(System.out::println);
        }
    }

}
