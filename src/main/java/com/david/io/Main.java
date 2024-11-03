package com.david.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

import static java.nio.file.StandardOpenOption.APPEND;

public class Main {

    final static String PATH_FILE = "src/main/java/com/david/io/test.txt";
    final static String PATH_DIRECTORY = "src/main/java/com/david/io/";

    public static void main(String[] args) {


    }

    private static void compressFileToGzip(String data) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream, Deflater.BEST_COMPRESSION);

            Writer writer = new OutputStreamWriter(gzipOutputStream);

            writer.write(data);
            writer.flush();

            gzipOutputStream.flush();
            gzipOutputStream.finish();

            byte[] bytes = byteArrayOutputStream.toByteArray();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            //use binary data to store in buckets or whatever

            byteArrayOutputStream.close();
            byteArrayInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
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
