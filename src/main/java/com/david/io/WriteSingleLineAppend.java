package com.david.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WriteSingleLineAppend {

    private static final String PATH_FILE = "src/main/resources/io/test.txt";

    static void main(String[] args) throws IOException {

        String newLine = "Nueva línea\n";

        Files.write(Path.of(PATH_FILE), newLine.getBytes(), StandardOpenOption.APPEND);
    }

}
