package com.david.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadAllLines {

    private static final String PATH_FILE = "src/main/resources/io/test.txt";

    static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of(PATH_FILE));

        lines.forEach(System.out::println);
    }

}
