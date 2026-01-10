package com.david.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ListFilesInDirectory {

    private static final String PATH_DIRECTORY = "src/main/resources/io/";

    static void main(String[] args) throws IOException {

        try (Stream<Path> pathStream = Files.list(Path.of(PATH_DIRECTORY))) {

            pathStream.filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }

    }

}
