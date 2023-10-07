package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class Main {

    final static String PATH_FILE = "src/io/test.txt";
    final static String PATH_DIRECTORY = "src/io/";

    public static void main(String[] args) {

        try {
            //readAllLines();
            //writeNewLine("test 3");
            //writeNewLines(Arrays.asList("test 4", "test 5"));
            readAllFilesInDirectory();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void readAllLines() throws IOException {

        List<String> allLines = Files.readAllLines(Paths.get(PATH_FILE));

        for (String line : allLines) {
            System.out.println(line);
        }

    }

    public static void writeNewLine(String newLine) throws IOException {
        Path file = Paths.get(PATH_FILE);

        byte[] bytes = newLine.getBytes();

        Files.write(file, bytes, APPEND);

    }

    public static void writeNewLines(List<String> newLines) throws IOException {
        Path file = Paths.get(PATH_FILE);

        Files.write(file, newLines, APPEND);
    }

    public static void readAllFilesInDirectory() throws IOException {

        Files.list(Paths.get(PATH_DIRECTORY))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
    }
}
