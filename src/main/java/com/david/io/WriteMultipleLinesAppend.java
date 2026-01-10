package com.david.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WriteMultipleLinesAppend {

    private static final String PATH_FILE = "src/main/resources/io/test.txt";

    static void main(String[] args) throws IOException {

        List<String> lines = List.of(
                "Primera línea",
                "Segunda línea",
                "Tercera línea"
        );

        Files.write(Path.of(PATH_FILE), lines, StandardOpenOption.APPEND);
    }

}
